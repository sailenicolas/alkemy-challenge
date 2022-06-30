package ar.com.saile.controllers;

import ar.com.saile.domain.ExceptionResponse;
import ar.com.saile.exceptions.BindingResultException;
import ar.com.saile.exceptions.LoginFailedException;
import ar.com.saile.exceptions.RecordException;
import ar.com.saile.exceptions.RegisterFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordException.RecordNotFoundException.class)
    protected ResponseEntity<ExceptionResponse> handleRecordNotFoundException(RecordException.RecordNotFoundException ex, WebRequest request) {

        ExceptionResponse exceptionResponse = exceptionResponseWithMessage( ex.getMessage(), HttpStatus.NOT_FOUND );
        return new ResponseEntity<>( exceptionResponse, NOT_FOUND );
    }

    @ExceptionHandler(LoginFailedException.class)
    protected ResponseEntity<ExceptionResponse> handleLoginFailed(LoginFailedException ex, WebRequest request) {

        ExceptionResponse exceptionResponse = exceptionResponseWithMessage( ex.getMessage(), HttpStatus.UNAUTHORIZED );
        return new ResponseEntity<>( exceptionResponse, HttpStatus.UNAUTHORIZED );
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionResponse error = exceptionResponseWithMessage( ex.getMessage(), status );
        return new ResponseEntity<>( error, status );
    }

    @ExceptionHandler(RegisterFailedException.class)
    protected ResponseEntity<?> handleRegisterFailure(RegisterFailedException ex, WebRequest request) {

        ExceptionResponse exceptionResponse = exceptionResponseWithMessage( ex.getMessage(), HttpStatus.UNAUTHORIZED );
        return new ResponseEntity<>( exceptionResponse, HttpStatus.UNAUTHORIZED );
    }
    @ExceptionHandler(RegisterFailedException.UsernameAlreadyTakenException.class)
    protected ResponseEntity<?> handleRegisterFailedUsernameALreadyTaken(RegisterFailedException.UsernameAlreadyTakenException ex, WebRequest request) {

        ExceptionResponse exceptionResponse = exceptionResponseWithMessage( ex.getMessage(), BAD_REQUEST );
        return new ResponseEntity<>( exceptionResponse, BAD_REQUEST );
    }

    protected static ExceptionResponse exceptionResponseWithMessage(String message, HttpStatus httpStatus) {

        Map<String, Object> tokens = Map.of(
                "errorMessage", message,
                "errorCode", httpStatus.series()
        );
        return new ExceptionResponse( httpStatus.getReasonPhrase(), tokens );
    }


    @ExceptionHandler(BindingResultException.class)
    protected ResponseEntity<ExceptionResponse> handleBindingResultException(BindingResultException ex, WebRequest request) {

        Map<String, Object> tokens = new HashMap<>();
        List<Map<String, Object>> token2 = ex.getFieldErrors().stream().map( vale -> {
            String defaultMessage = "FIELD ERROR";
            String code = "CODE ERROR";
            if(vale.getDefaultMessage() != null)
                defaultMessage = vale.getDefaultMessage();
            if(vale.getCode() != null)
                code = vale.getCode();
            return Map.<String, Object>ofEntries(
            Map.entry( "message", defaultMessage ),
            Map.entry( "code", code ),
            Map.entry( "field", vale.getField() ),
            Map.entry( "extra", vale.getObjectName() )
            );
        } ).collect( Collectors.toList() );

        tokens.put( "errorMessage", "Hay errores en lo enviado" );
        tokens.put( "errorFields", token2 );
        tokens.put( "errorCode", BAD_REQUEST.series() );
        ExceptionResponse error = new ExceptionResponse( BAD_REQUEST.getReasonPhrase(), tokens );
        return new ResponseEntity<>( error, BAD_REQUEST );
    }
}
