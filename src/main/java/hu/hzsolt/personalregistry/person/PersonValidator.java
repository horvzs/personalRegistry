package hu.hzsolt.personalregistry.person;

import hu.hzsolt.personalregistry.address.AddressDto;
import hu.hzsolt.personalregistry.error.DuplicateEntryException;
import org.springframework.stereotype.Component;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
@Component
public class PersonValidator {

    public void checkDifferenceAddresses(PersonDto personDto) {
        AddressDto firstAddress = personDto.getAddresses().get(0);
        AddressDto secondAddress = personDto.getAddresses().get(1);
        if (personDto.getAddresses().get(0).equals(personDto.getAddresses().get(1))) {
            throw new DuplicateEntryException("The two addresses are the same");
        }
        if (
                firstAddress.getCity().equals(secondAddress.getCity()) &&
                firstAddress.getZipCode() == (secondAddress.getZipCode()) &&
                firstAddress.getStreet().equals(secondAddress.getStreet())
        ) {
            throw new DuplicateEntryException("The two addresses are the same");
        }
    }
}
