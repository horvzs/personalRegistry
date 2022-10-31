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
    private final PersonProvider personProvider;
    private final PersonRemover personRemover;
    private final PersonUpdater personUpdater;

    @Autowired
    public PersonController(
            PersonPersister personPersister, PersonProvider personProvider, PersonRemover personRemover,
            PersonUpdater personUpdater) {
        this.personPersister = personPersister;
        this.personProvider = personProvider;
        this.personRemover = personRemover;
        this.personUpdater = personUpdater;
    }

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonCreated savePerson(@Valid @RequestBody PersonDto personDto) {
        return personPersister.persistPerson(personDto);
    }

    @GetMapping("/person/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPerson(@PathVariable Long id) {
        return personProvider.getPersonById(id);
    }

    @DeleteMapping("/person/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGuest(@PathVariable Long id) {
        personRemover.deletePerson(id);
    }

    @PutMapping("/person/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyGuest(@Valid @RequestBody PersonDto personDto, @PathVariable Long id) {
        personUpdater.updatePerson(personDto, id);
    }

}
