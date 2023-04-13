package com.mokasoft.gestresto.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationResponse {
    private HttpStatus response;
    private List<String> errors;
    private String uri;

    public ValidationResponse() {
        this.errors = new ArrayList<>();
    }

    public void addError(String error){
        this.errors.add(error);
    }
}
