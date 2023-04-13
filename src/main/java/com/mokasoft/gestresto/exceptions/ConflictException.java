package com.mokasoft.gestresto.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiBaseException{

    public ConflictException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }

}
