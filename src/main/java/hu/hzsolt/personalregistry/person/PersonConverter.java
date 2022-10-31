package hu.hzsolt.personalregistry.person;

import hu.hzsolt.personalregistry.address.Address;
import hu.hzsolt.personalregistry.address.AddressConverter;
import hu.hzsolt.personalregistry.address.AddressDto;
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
public class PersonConverter {

    private final AddressConverter addressConverter;

    @Autowired
    public PersonConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonConverter.class);

    public Person convertToEntity(PersonDto personDto) {
        Person person = new Person()
                .setName(personDto.getName())
                .setAddresses(convertAddressDtoListToEntity(personDto.getAddresses()));

        LOGGER.debug("Resource conversation from dto to entity is successfully");
        return person;
    }

    public PersonDto convertToDto(Person person) {
        PersonDto personDto = new PersonDto()
                .setName(person.getName())
                .setAddresses(convertAddressListToDto(person.getAddresses()));

        LOGGER.debug("Resource conversation from entity to dto is successfully");
        return personDto;
    }

    private List<Address> convertAddressDtoListToEntity(List<AddressDto> addresses) {
        List<Address> addressList = new ArrayList<>();
        for (AddressDto address : addresses) {
            addressList.add(addressConverter.convertToEntity(address));
        }
        return addressList;
    }

    private List<AddressDto> convertAddressListToDto(List<Address> addresses) {
        List<AddressDto> addressList = new ArrayList<>();
        for (Address address : addresses) {
            addressList.add(addressConverter.convertToDto(address));
        }
        return addressList;
    }
}
