package hu.hzsolt.personalregistry.address;

import hu.hzsolt.personalregistry.contact.Contact;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "ZipCode cannot be null")
    private int zipCode;

    @NotEmpty(message = "City cannot be empty")
    private String city;

    @NotEmpty(message = "Street cannot be empty")
    private String street;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private List<Contact> contacts;

    public Long getId() {
        return id;
    }

    public Address setId(Long id) {
        this.id = id;
        return this;
    }

    public int getZipCode() {
        return zipCode;
    }

    public Address setZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public Address setAddressType(AddressType addressType) {
        this.addressType = addressType;
        return this;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Address setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return zipCode == address.zipCode && Objects.equals(id, address.id) &&
                Objects.equals(city, address.city) && Objects.equals(street, address.street) &&
                addressType == address.addressType && Objects.equals(contacts, address.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zipCode, city, street, addressType, contacts);
    }
}
