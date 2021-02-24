package com.drools.demo.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> handleConstraintException(MethodArgumentNotValidException ex, WebRequest request){
        BindingResult result = ex.getBindingResult();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(),"Validation_Failed",result.getFieldErrors());
        return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex){
        List<String> errorsList=new ArrayList<>();
        Set<ConstraintViolation<?>> constraintViolations=ex.getConstraintViolations();
       for(ConstraintViolation constraintViolation:constraintViolations){
           errorsList.add(constraintViolation.getMessage());
       }
        ApiError apiError=new ApiError(errorsList, HttpStatus.BAD_REQUEST.value(),"Validation_Failed");
        return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ApiError> resourceNotFound(ResourceNotFoundException ex){
        ApiError apiError=new ApiError(HttpStatus.NOT_FOUND.value(),ex.getMessage(),ex.getMessage());
        return new ResponseEntity<ApiError>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ApiError> badRequestFound(BadRequestException ex){
        ApiError apiError=new ApiError(HttpStatus.BAD_REQUEST.value(),ex.getLocalizedMessage(),ex.getMessage());
        return new ResponseEntity<ApiError>(apiError,HttpStatus.BAD_REQUEST);
    }

}
