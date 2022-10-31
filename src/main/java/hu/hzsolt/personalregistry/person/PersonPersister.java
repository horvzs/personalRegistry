package hu.hzsolt.personalregistry.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
@Service
public class PersonPersister {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonPersister.class);
    private final PersonRepository personRepository;
    private final PersonConverter personConverter;
    private final PersonValidator personValidator;

    @Autowired
    public PersonPersister(
            PersonRepository personRepository, PersonConverter personConverter, PersonValidator personValidator
    ) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
        this.personValidator = personValidator;
    }

    public PersonCreated persistPerson(PersonDto personDto) {
        if (personDto.getAddresses().size() == 2) {
            personValidator.checkDifferenceAddresses(personDto);
        }
        LOGGER.debug("Person validation is successfully");
        Person person = personConverter.convertToEntity(personDto);
        personRepository.save(person);
        LOGGER.info("Person is saved with id {%s}".formatted(person.getId()));
        return new PersonCreated().setId(person.getId());
    }
}
