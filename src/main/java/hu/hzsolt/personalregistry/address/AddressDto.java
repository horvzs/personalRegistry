package hu.hzsolt.personalregistry.address;

import hu.hzsolt.personalregistry.contact.ContactDto;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
public class AddressDto {

    @NotEmpty
    private int zipCode;

    @NotEmpty
    private String city;

    @NotEmpty
    private String street;

    @NotEmpty
    private AddressType addressType;

    @NotEmpty
    private List<ContactDto> contacts;

    public int getZipCode() {
        return zipCode;
    }

    public AddressDto setZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public AddressDto setAddressType(AddressType addressType) {
        this.addressType = addressType;
        return this;
    }

    public List<ContactDto> getContacts() {
        return contacts;
    }

    public AddressDto setContacts(List<ContactDto> contacts) {
        this.contacts = contacts;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto that = (AddressDto) o;
        return Objects.equals(zipCode, that.zipCode) && Objects.equals(city, that.city) &&
                Objects.equals(street, that.street) && addressType == that.addressType &&
                Objects.equals(contacts, that.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, city, street, addressType, contacts);
    }
}
