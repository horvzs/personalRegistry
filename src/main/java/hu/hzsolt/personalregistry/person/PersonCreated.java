package hu.hzsolt.personalregistry.person;

import java.util.Objects;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
public class PersonCreated {

    private Long id;

    public Long getId() {
        return id;
    }

    public PersonCreated setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonCreated that = (PersonCreated) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
