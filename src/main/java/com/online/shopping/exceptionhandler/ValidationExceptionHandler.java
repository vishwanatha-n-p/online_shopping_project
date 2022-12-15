package com.online.shopping.exceptionhandler;

import com.online.shopping.errormodel.ErrorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ErrorModel errorModel;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        errorModel.setHttpStatus(status);
        errorModel.setLocalDateTime(LocalDateTime.now());
        errorModel.setMessage("Validation error");
        errorModel.setDetails(ex.getBindingResult().toString());
        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }

}
