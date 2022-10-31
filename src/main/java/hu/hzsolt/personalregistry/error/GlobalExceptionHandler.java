package hu.hzsolt.personalregistry.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException exception) {

        LOGGER.error(exception.getMessage(), exception);
        return new ResponseEntity<>(createErrorResponse(exception, HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach(
                (error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String defaultMessage = error.getDefaultMessage();
                    errors.put(fieldName, defaultMessage);
                }
        );
        ValidationErrorResponse validationErrorResponse =
                new ValidationErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(), errors);
        ;
        LOGGER.error("Validation error: %s".formatted(validationErrorResponse.getErrors().toString()));
        return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(ResourceNotExistsException exception) {

        LOGGER.error(exception.getMessage(), exception);
        return new ResponseEntity<>(createErrorResponse(exception, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEntryException(DuplicateEntryException exception) {

        LOGGER.error(exception.getMessage(), exception);
        return new ResponseEntity<>(createErrorResponse(exception, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse createErrorResponse(Exception exception, HttpStatus httpStatus) {
        return new ErrorResponse(httpStatus, LocalDateTime.now(), exception.getMessage());
    }
}
