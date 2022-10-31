package hu.hzsolt.personalregistry.error;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
public class DuplicateEntryException extends RuntimeException {

    public DuplicateEntryException(String message) {
        super(message);
    }
}
