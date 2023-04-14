package com.mokasoft.gestresto.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> responseBuilder(String message
            ,HttpStatus httpStatus , Object responseObject){
        Map<String,Object> response = new HashMap<>();
        response.put("response",httpStatus);
        response.put("message", message);
        //response.put("data",responseObject);

        return new ResponseEntity<>(response,httpStatus);
    }
}
