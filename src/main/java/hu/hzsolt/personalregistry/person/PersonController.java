package hu.hzsolt.personalregistry.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
@RestController
public class PersonController {

    private final PersonPersister personPersister;

    @Autowired
    public PersonController(PersonPersister personPersister) {
        this.personPersister = personPersister;
    }

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonCreated savePerson(@Valid @RequestBody PersonDto personDto) {
        return personPersister.persistPerson(personDto);
    }
}
