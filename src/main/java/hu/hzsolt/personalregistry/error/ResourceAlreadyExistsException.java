package hu.hzsolt.personalregistry.error;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
