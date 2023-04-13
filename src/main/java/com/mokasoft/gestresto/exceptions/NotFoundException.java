package com.mokasoft.gestresto.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiBaseException{

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
