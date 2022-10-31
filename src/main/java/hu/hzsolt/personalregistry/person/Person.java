package hu.hzsolt.personalregistry.person;

import hu.hzsolt.personalregistry.address.Address;

import javax.persistence.*;
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

    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    @Size(min = 1,max = 2)
    private Set<Address> addresses;

}
