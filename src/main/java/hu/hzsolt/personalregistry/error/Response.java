package hu.hzsolt.personalregistry.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
public abstract class Response {

    protected HttpStatus httpStatus;
    protected LocalDateTime atDate;

    public Response(HttpStatus httpStatus, LocalDateTime atDate) {
        this.httpStatus = httpStatus;
        this.atDate = atDate;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getAtDate() {
        return atDate;
    }

    public void setAtDate(LocalDateTime atDate) {
        this.atDate = atDate;
    }
}
