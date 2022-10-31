package hu.hzsolt.personalregistry.person;

import hu.hzsolt.personalregistry.address.Address;
import hu.hzsolt.personalregistry.address.AddressConverter;
import hu.hzsolt.personalregistry.address.AddressDto;
import hu.hzsolt.personalregistry.address.AddressType;
import hu.hzsolt.personalregistry.contact.Contact;
import hu.hzsolt.personalregistry.contact.ContactConverter;
import hu.hzsolt.personalregistry.contact.ContactDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
public class PersonConverterTest {

    @Test
    public void testConvertToEntity_usingValidArguments_returnsOk() {
        // Arrange
        ContactDto contactDto = new ContactDto()
                .setEmail("test1@email.com")
                .setPhone("+36301111555")
                ;

        ContactDto contactDto2 = new ContactDto()
                .setEmail("test2@email.com")
                .setPhone("+36301441555")
                ;
        AddressDto addressDto = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("Budapest")
                .setStreet("Jókai 25")
                .setZipCode(1000)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        AddressDto addressDto2 = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("Győr")
                .setStreet("Teszt 25")
                .setZipCode(5252)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        PersonDto personDto = new PersonDto()
                .setName("TestDto")
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
                .setName("TestDto")
                .setAddresses(Arrays.asList(address, address2))
                ;

        // Act
        PersonConverter personConverter = new PersonConverter(new AddressConverter(new ContactConverter()));
        Person convertedPerson = personConverter.convertToEntity(personDto);

        // Assert
        Assertions.assertEquals(convertedPerson, person);
    }
}
