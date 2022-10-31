package hu.hzsolt.personalregistry.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author zshorvath
 * created on 31/10/2022
 */

public class ErrorResponse extends Response{

    private String errorMessage;

    public ErrorResponse(HttpStatus httpStatus, LocalDateTime atDate, String errorMessage) {
        super(httpStatus, atDate);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}