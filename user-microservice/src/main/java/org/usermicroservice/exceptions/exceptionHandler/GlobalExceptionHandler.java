package org.usermicroservice.exceptions.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.usermicroservice.dtos.ErrorBody;
import org.usermicroservice.exceptions.*;
import java.util.Collections;
import java.util.Date;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataNotValidException.class)
    public ResponseEntity<?> handleAlreadyExistsException(DataNotValidException ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_ACCEPTABLE, Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }
    @ExceptionHandler(EmailAlreadyExist.class)
    public ResponseEntity<?> handleUserNotFound(EmailAlreadyExist ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_FOUND, Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }

    @ExceptionHandler(EmptyEntityException.class)
    public ResponseEntity<?> handleAlreadyExistsException(EmptyEntityException ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_ACCEPTABLE, Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }
    @ExceptionHandler(RoleNoteFoundException.class)
    public ResponseEntity<?> handleUserNotFound(RoleNoteFoundException ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_FOUND, Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_FOUND, Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }

}
