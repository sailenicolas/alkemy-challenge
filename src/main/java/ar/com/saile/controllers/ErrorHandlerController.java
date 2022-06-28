package ar.com.saile.controllers;

import ar.com.saile.domain.ExceptionResponse;
import ar.com.saile.exceptions.BindingResultException;
import ar.com.saile.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RecordNotFoundException.class)
    protected ResponseEntity<ExceptionResponse> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {

        Map<String, Object> tokens = new HashMap<>();
        tokens.put("errorMessage", ex.getMessage());
        tokens.put("errorCode", NOT_FOUND.series());
        ExceptionResponse error = new ExceptionResponse(NOT_FOUND.getReasonPhrase(), tokens);
        return new ResponseEntity<>(error, NOT_FOUND);
    }


    @ExceptionHandler(BindingResultException.class)
    protected ResponseEntity<ExceptionResponse> handleBindingResultException(BindingResultException ex, WebRequest request) {

        Map<String, Object> tokens = new HashMap<>();
        var token2 = ex.getFieldErrors().stream().map(vale -> {
            Map<String, Object> tokens2 = new HashMap<>();
            tokens2.put("message", vale.getDefaultMessage());
            tokens2.put("code", vale.getCode());
            tokens2.put("field", vale.getField());
            tokens2.put("extra", vale.getObjectName());
            return tokens2;
        }).collect(Collectors.toList());

        tokens.put("errorMessage", "Hay errores en lo enviado");
        tokens.put("errorFields", token2);
        tokens.put("errorCode", BAD_REQUEST);
        ExceptionResponse error = new ExceptionResponse(BAD_REQUEST.getReasonPhrase(), tokens);
        return new ResponseEntity<>(error, BAD_REQUEST);
    }
}
