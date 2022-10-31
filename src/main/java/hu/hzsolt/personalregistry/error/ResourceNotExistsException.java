package hu.hzsolt.personalregistry.error;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
public class ResourceNotExistsException extends RuntimeException {

    public ResourceNotExistsException(String message) {
        super(message);
    }
}
