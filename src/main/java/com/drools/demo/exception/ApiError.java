package com.drools.demo.exception;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class ApiError {
    private int status;
    private String message;
    private Date timestamp;
    private List<String> Errors = new ArrayList<>();
    ApiError(int status, String message, List<FieldError> fieldErrors){
        this.status=status;
        this.message=message;
        this.timestamp=new Date();
        for(FieldError error:fieldErrors){
            Errors.add(error.getDefaultMessage());
        }
    }
    ApiError(List<String> errors, int status, String message){
        this.setErrors(errors);
        this.setMessage(message);
        this.setStatus(status);
        this.timestamp=new Date();
    }
    ApiError(int status, String message, String error){
        this.status=status;
        this.message=message;
        this.setErrors(Arrays.asList(error));
        this.timestamp=new Date();
    }
}