package hu.hzsolt.personalregistry.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
public class ValidationErrorResponse extends Response {

    private Map<String, String> errors;

    public ValidationErrorResponse(HttpStatus httpStatus, LocalDateTime atDate, Map<String, String> errors) {
        super(httpStatus, atDate);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
