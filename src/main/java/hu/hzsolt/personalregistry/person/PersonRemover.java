package hu.hzsolt.personalregistry.person;

import hu.hzsolt.personalregistry.error.ResourceNotExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
@Service
public class PersonRemover {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRemover.class);
    private final PersonRepository personRepository;

    @Autowired
    public PersonRemover(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void deletePerson(Long id) {
        if (! personRepository.existsById(id)) {
            throw new ResourceNotExistsException("Resource is not exists by id {%s}".formatted(id));
        }
        personRepository.deleteById(id);
        LOGGER.info("Resource is deleted by it {%s}".formatted(id));
    }
}
