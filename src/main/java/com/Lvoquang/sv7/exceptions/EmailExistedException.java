package com.Lvoquang.sv7.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.CONFLICT)
public class EmailExistedException extends RuntimeException {
    public EmailExistedException() {
        super("This email already exist");
    }
}
