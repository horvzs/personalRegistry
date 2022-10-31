package hu.hzsolt.personalregistry.person;

import hu.hzsolt.personalregistry.address.AddressDto;
import hu.hzsolt.personalregistry.address.AddressType;
import hu.hzsolt.personalregistry.contact.ContactDto;
import hu.hzsolt.personalregistry.error.DuplicateEntryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
public class PersonValidatorTest {

    @Test
    public void testCheckDifferenceAddresses_usingValidArguments_returnsOk() {
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
                .setStreet("Teszt street 25")
                .setZipCode(1000)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        AddressDto addressDto2 = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("Test City")
                .setStreet("Teszt 25")
                .setZipCode(5252)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        PersonDto personDto = new PersonDto()
                .setName("TestDto")
                .setAddresses(Arrays.asList(addressDto, addressDto2))
                ;

        // Act && Assert
        Assertions.assertDoesNotThrow(
                () -> new PersonValidator().checkDifferenceAddresses(personDto)
        );
    }

    @Test
    public void testCheckDifferenceAddresses_usingSameAddresses_throwsDuplicateEntryException() {
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
                () -> new PersonValidator().checkDifferenceAddresses(personDto)
        );
    }
}
