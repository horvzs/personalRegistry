package hu.hzsolt.personalregistry.person;

import hu.hzsolt.personalregistry.address.Address;
import hu.hzsolt.personalregistry.address.AddressConverter;
import hu.hzsolt.personalregistry.address.AddressDto;
import hu.hzsolt.personalregistry.address.AddressType;
import hu.hzsolt.personalregistry.contact.Contact;
import hu.hzsolt.personalregistry.contact.ContactConverter;
import hu.hzsolt.personalregistry.contact.ContactDto;
import hu.hzsolt.personalregistry.error.DuplicateEntryException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author zshorvath
 * created on 31/10/2022
 */

public class PersonUpdaterTest {

    @Mock
    private PersonRepository personRepository;

    private PersonUpdater underTest;

    @BeforeEach
    public void setup() {
        underTest =
                new PersonUpdater(
                        personRepository,
                        new PersonConverter(new AddressConverter(new ContactConverter())), new PersonValidator()
                );
    }

    @Test
    public void testUpdatePerson_usingValidArguments_returnsOk() {
        // Arrange
        ContactDto contactDto = new ContactDto()
                .setEmail("base@email.com")
                .setPhone("+36301111555")
                ;

        ContactDto contactDto2 = new ContactDto()
                .setEmail("base2@email.com")
                .setPhone("+36301441555")
                ;
        AddressDto addressDto = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("BaseCity")
                .setStreet("Base street 25")
                .setZipCode(1000)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        AddressDto addressDto2 = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("BaseCity2")
                .setStreet("Base Street 10")
                .setZipCode(5252)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        PersonDto basePersonDto = new PersonDto()
                .setName("BaseDto")
                .setAddresses(Arrays.asList(addressDto, addressDto2))
                ;

        Contact contact = new Contact()
                .setEmail("test1@email.com")
                .setPhone("+36301111555")
                ;

        Contact contact2 = new Contact()
                .setEmail("test2@email.com")
                .setPhone("+36301441555")
                ;
        Address address = new Address()
                .setAddressType(AddressType.PERMANENT)
                .setCity("Budapest")
                .setStreet("Jókai 25")
                .setZipCode(1000)
                .setContacts(Arrays.asList(contact, contact2))
                ;
        Address address2 = new Address()
                .setAddressType(AddressType.PERMANENT)
                .setCity("Győr")
                .setStreet("Teszt 25")
                .setZipCode(5252)
                .setContacts(Arrays.asList(contact, contact2))
                ;
        Person person = new Person()
                .setId(1L)
                .setName("TestDto")
                .setAddresses(Arrays.asList(address, address2))
                ;

        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        // Act && Assert
        Assertions.assertDoesNotThrow(
                () -> underTest.updatePerson(basePersonDto, 1L)
        );

    }

    @Test
    public void testUpdatePerson_usingSameAddresses_throwsDuplicateEntryException() {
        // Arrange
        ContactDto contactDto = new ContactDto()
                .setEmail("test1@email.com")
                .setPhone("+36301111555")
                ;

        ContactDto contactDto2 = new ContactDto()
                .setEmail("test1@email.com")
                .setPhone("+36301111555")
                ;
        AddressDto addressDto = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("Budapest")
                .setStreet("Teszt street 25")
                .setZipCode(1000)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        AddressDto addressDto2 = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("Budapest")
                .setStreet("Teszt street 25")
                .setZipCode(1000)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        PersonDto personDto = new PersonDto()
                .setName("TestDto")
                .setAddresses(Arrays.asList(addressDto, addressDto2))
                ;

        // Act && Assert
        Assertions.assertThrows(
                DuplicateEntryException.class,
                () -> underTest.updatePerson(personDto, 1L)
        );
    }
}
