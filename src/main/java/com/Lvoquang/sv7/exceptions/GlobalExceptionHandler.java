package com.Lvoquang.sv7.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
class ErrorModel {
    private List<String> message;
    private String path;
}

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorModel> handleNotFoundException(NotFoundException e, ServletWebRequest request) {
        return new ResponseEntity<>(new ErrorModel(Collections.singletonList(e.getMessage()),
                request.getRequest().getRequestURI()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorModel> handleUserNotFoundException(UserNotFoundException e, ServletWebRequest request) {
        return new ResponseEntity<>(new ErrorModel(Collections.singletonList(e.getMessage()),
                request.getRequest().getRequestURI()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidRoleException.class})
    public ResponseEntity<ErrorModel> handleRoleInvalidException(InvalidRoleException e, ServletWebRequest request) {
        return new ResponseEntity<>(new ErrorModel(Collections.singletonList(e.getMessage()),
                request.getRequest().getRequestURI()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({RoleExistedException.class})
    public ResponseEntity<ErrorModel> handleRoleExistedException(RoleExistedException e, ServletWebRequest request) {
        return new ResponseEntity<>(new ErrorModel(Collections.singletonList(e.getMessage()),
                request.getRequest().getRequestURI()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UserExistedException.class})
    public ResponseEntity<ErrorModel> handleUserExistedException(UserExistedException e, ServletWebRequest request) {
        return new ResponseEntity<>(new ErrorModel(Collections.singletonList(e.getMessage()),
                request.getRequest().getRequestURI()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler({EmailExistedException.class})
    public ResponseEntity<ErrorModel> handleEmailExistedException(EmailExistedException e, ServletWebRequest request) {
        return new ResponseEntity<>(new ErrorModel(Collections.singletonList(e.getMessage()),
                request.getRequest().getRequestURI()),
                HttpStatus.CONFLICT);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorModel> handleUnwantedException(Exception e, ServletWebRequest request) {
        return new ResponseEntity<>(new ErrorModel(Collections.singletonList(e.getMessage()),
                request.getRequest().getRequestURI()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}