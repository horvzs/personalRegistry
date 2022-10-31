package hu.hzsolt.personalregistry.person;

import hu.hzsolt.personalregistry.error.ResourceNotExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @author zshorvath
 * created on 31/10/2022
 */
@Service
public class PersonProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonProvider.class);

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    @Autowired
    public PersonProvider(PersonRepository personRepository, PersonConverter personConverter) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
    }

    public PersonDto getPersonById(Long id) {
        Optional<Person> personById = personRepository.findById(id);
        if (personById.isEmpty()) {
            throw new ResourceNotExistsException("Resource is not exists by id {%s}".formatted(id));
        }
        PersonDto personDto = personConverter.convertToDto(personById.get());
        LOGGER.info("Found resource by id {%s}".formatted(id));
        return personDto;
    }
}
