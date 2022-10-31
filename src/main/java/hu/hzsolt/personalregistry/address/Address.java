package hu.hzsolt.personalregistry.address;

import hu.hzsolt.personalregistry.contact.Contact;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "ZipCode cannot be empty")
    private String zipCode;

    @NotEmpty(message = "City cannot be empty")
    private String city;

    @NotEmpty(message = "Street cannot be empty")
    private String street;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Set<Contact> contacts;

    public Long getId() {
        return id;
    }

    public Address setId(Long id) {
        this.id = id;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Address setZipCode(String zipCode) {
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

    public Set<Contact> getContacts() {
        return contacts;
    }

    public Address setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }
}
