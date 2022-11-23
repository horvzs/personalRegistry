package hu.hzsolt.personalregistry.person;

import hu.hzsolt.personalregistry.address.Address;
import hu.hzsolt.personalregistry.contact.Contact;
import hu.hzsolt.personalregistry.error.ResourceNotExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
@Service
public class PersonUpdater {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonUpdater.class);

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;
    private final PersonValidator personValidator;

    @Autowired
    public PersonUpdater(
            PersonRepository personRepository, PersonConverter personConverter, PersonValidator personValidator
    ) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
        this.personValidator = personValidator;
    }

    public void updatePerson(PersonDto personDto, Long id) {
        if (personDto.getAddresses().size() == 2) {
            personValidator.checkDifferenceAddresses(personDto);
        }
        Optional<Person> personById = personRepository.findById(id);
        if (personById.isEmpty()) {
            throw new ResourceNotExistsException("Resource is not exists by id {%s}".formatted(id));
        }
        Person personFromDb = personById.get();
        Person updatePerson = personConverter.convertToEntity(personDto);
        updatePersonAddresses(updatePerson, personFromDb);
        personRepository.save(personFromDb);
        LOGGER.info("Resource modifying is successfully by id {%s}".formatted(id));
    }

    private void updatePersonAddresses(Person updatePerson, Person personFromDb) {
        List<Address> addressesFromDb = personFromDb.getAddresses();
        List<Address> updateAddresses = updatePerson.getAddresses();
        for (int i = 0; i < updateAddresses.size();) {
            for (Address oldAddress : addressesFromDb) {
                Address updateAddress = updateAddresses.get(i++);
                oldAddress
                        .setAddressType(updateAddress.getAddressType())
                        .setStreet(updateAddress.getStreet())
                        .setCity(updateAddress.getCity())
                        .setZipCode(updateAddress.getZipCode())
                        .setContacts(updateAddressContact(updateAddress, oldAddress))
                ;
            }
        }
        personFromDb.setName(updatePerson.getName());
    }

    private List<Contact> updateAddressContact(Address updateAddress, Address addressFromDb) {
        List<Contact> contactsFromDb = addressFromDb.getContacts();
        List<Contact> updateContacts = updateAddress.getContacts();
        for (int i = 0; i < updateContacts.size();) {
            for (Contact oldContact : contactsFromDb) {
                Contact updateContact = updateContacts.get(i++);
                oldContact
                        .setPhone(updateContact.getPhone())
                        .setEmail(updateContact.getEmail())
                ;
            }
        }
        return contactsFromDb;
    }

    public void asd() {

    }
}
