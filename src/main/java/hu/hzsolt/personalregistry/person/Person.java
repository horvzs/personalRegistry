package hu.hzsolt.personalregistry.person;

import hu.hzsolt.personalregistry.address.Address;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    @Size(min = 1,max = 2)
    private Set<Address> addresses;

    public Long getId() {
        return id;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public Person setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
        return this;
    }
}
