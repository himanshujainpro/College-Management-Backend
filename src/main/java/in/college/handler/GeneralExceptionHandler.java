package in.college.handler;

import in.college.exception.BadCredentialsException;
import in.college.exception.DataNotExistException;
import in.college.exception.DataNotValidException;
import in.college.exception.DuplicateEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.sql.SQLException;

@ControllerAdvice
public class GeneralExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<Object> handleDuplicateEntity(DuplicateEntityException e) {
        String message=e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSqlException(SQLException e) {
        String message=e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DataNotExistException.class)
    public ResponseEntity<Object> handleDataNotExistException(DataNotExistException e) {
        String message=e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DataNotValidException.class)
    public ResponseEntity<Object> handleDataNotValidException(DataNotValidException e) {
        String message=e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException e) {
        String message=e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String message=e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}