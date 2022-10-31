package hu.hzsolt.personalregistry.address;

import hu.hzsolt.personalregistry.contact.Contact;

import javax.persistence.*;
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

    private String zipCode;
    private String city;
    private String street;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Set<Contact> contacts;
}
