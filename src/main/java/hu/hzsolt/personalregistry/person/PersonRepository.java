package hu.hzsolt.personalregistry.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
