package hu.hzsolt.personalregistry.address;

import hu.hzsolt.personalregistry.contact.Contact;
import hu.hzsolt.personalregistry.contact.ContactConverter;
import hu.hzsolt.personalregistry.contact.ContactDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
@Component
public class AddressConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressConverter.class);
    private final ContactConverter contactConverter;

    @Autowired
    public AddressConverter(ContactConverter contactConverter) {
        this.contactConverter = contactConverter;
    }

    public Address convertToEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setAddressType(addressDto.getAddressType());
        address.setCity(addressDto.getCity());
        address.setContacts(convertContactDtoListToEntity(addressDto.getContacts()));
        address.setStreet(addressDto.getStreet());
        address.setZipCode(addressDto.getZipCode());

        LOGGER.debug("Address entity conversion is successfully ");
        return address;

    }

    public AddressDto convertToDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressType(address.getAddressType());
        addressDto.setCity(address.getCity());
        addressDto.setContacts(convertContactListToDto(address.getContacts()));
        addressDto.setStreet(address.getStreet());
        addressDto.setZipCode(address.getZipCode());

        LOGGER.debug("Address dto conversion is successfully ");
        return addressDto;
    }

    private List<Contact> convertContactDtoListToEntity(List<ContactDto> contacts) {
        List<Contact> contactsEntity = new ArrayList<>();
        for (ContactDto contact : contacts) {
            contactsEntity.add(contactConverter.convertToEntity(contact));
        }
        return contactsEntity;
    }

    private List<ContactDto> convertContactListToDto(List<Contact> contacts) {
        List<ContactDto> contactList = new ArrayList<>();
        for (Contact contact : contacts) {
            contactList.add(contactConverter.convertToDto(contact));
        }
        return contactList;
    }
}
