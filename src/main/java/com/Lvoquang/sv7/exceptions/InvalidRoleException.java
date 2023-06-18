package com.Lvoquang.sv7.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException() {
        super("Invalid role");
    }

}
