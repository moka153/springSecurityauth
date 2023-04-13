package com.mokasoft.gestresto.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ApiExceptionResponse {
    private final String message;
    private final HttpStatus httpStatus;
    private final String uri;
}
