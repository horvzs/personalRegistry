package hu.hzsolt.personalregistry.person;

import hu.hzsolt.personalregistry.address.AddressDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
public class PersonDto {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Size(
            min = 1,max = 2,
            message = "Address list must contain at least 1, max 2 address"
    )
    private List<AddressDto> addresses;

    public String getName() {
        return name;
    }

    public PersonDto setName(String name) {
        this.name = name;
        return this;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public PersonDto setAddresses(List<AddressDto> addresses) {
        this.addresses = addresses;
        return this;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto personDto = (PersonDto) o;
        return Objects.equals(name, personDto.name) && Objects.equals(addresses, personDto.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, addresses);
    }
}
