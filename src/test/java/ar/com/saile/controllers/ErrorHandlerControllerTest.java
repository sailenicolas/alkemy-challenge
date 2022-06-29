package ar.com.saile.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.saile.domain.ExceptionResponse;
import ar.com.saile.exceptions.BindingResultException;
import ar.com.saile.exceptions.RecordNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

class ErrorHandlerControllerTest {

    /**
     * Method under test: {@link ErrorHandlerController#handleRecordNotFoundException(RecordNotFoundException, WebRequest)}
     */
    @Test
    void testHandleRecordNotFoundException() {

        ErrorHandlerController errorHandlerController = new ErrorHandlerController();
        RecordNotFoundException ex = new RecordNotFoundException( "An error occurred" );
        ResponseEntity<ExceptionResponse> actualHandleRecordNotFoundExceptionResult = errorHandlerController
                .handleRecordNotFoundException( ex, new ServletWebRequest( new MockHttpServletRequest() ) );
        assertTrue( actualHandleRecordNotFoundExceptionResult.hasBody() );
        assertTrue( actualHandleRecordNotFoundExceptionResult.getHeaders().isEmpty() );
        assertEquals( HttpStatus.NOT_FOUND, actualHandleRecordNotFoundExceptionResult.getStatusCode() );
        ExceptionResponse body = actualHandleRecordNotFoundExceptionResult.getBody();
        assertEquals( "Not Found", body.getMessage() );
        assertEquals( 2, body.getDetails().size() );
    }

    /**
     * Method under test: {@link ErrorHandlerController#handleRecordNotFoundException(RecordNotFoundException, WebRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleRecordNotFoundException2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "ar.com.saile.exceptions.RecordNotFoundException.getMessage()" because "ex" is null
        //       at ar.com.saile.controllers.ErrorHandlerController.handleRecordNotFoundException(ErrorHandlerController.java:25)
        //   In order to prevent handleRecordNotFoundException(RecordNotFoundException, WebRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   handleRecordNotFoundException(RecordNotFoundException, WebRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        ErrorHandlerController errorHandlerController = new ErrorHandlerController();
        errorHandlerController.handleRecordNotFoundException( null, new ServletWebRequest( new MockHttpServletRequest() ) );
    }

    /**
     * Method under test: {@link ErrorHandlerController#handleBindingResultException(BindingResultException, WebRequest)}
     */
    @Test
    void testHandleBindingResultException() {

        ErrorHandlerController errorHandlerController = new ErrorHandlerController();
        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( new ArrayList<>() );
        BindingResultException ex = new BindingResultException(
                new BindException( new BindException( new BindException( new BindException( new BindException(
                        new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) ) ) ) ) ) );
        ResponseEntity<ExceptionResponse> actualHandleBindingResultExceptionResult = errorHandlerController
                .handleBindingResultException( ex, new ServletWebRequest( new MockHttpServletRequest() ) );
        assertTrue( actualHandleBindingResultExceptionResult.hasBody() );
        assertTrue( actualHandleBindingResultExceptionResult.getHeaders().isEmpty() );
        assertEquals( HttpStatus.BAD_REQUEST, actualHandleBindingResultExceptionResult.getStatusCode() );
        ExceptionResponse body = actualHandleBindingResultExceptionResult.getBody();
        assertEquals( "Bad Request", body.getMessage() );
        assertEquals( 3, body.getDetails().size() );
        verify( bindingResult ).getFieldErrors();
    }

    /**
     * Method under test: {@link ErrorHandlerController#handleBindingResultException(BindingResultException, WebRequest)}
     */
    @Test
    void testHandleBindingResultException2() {

        ErrorHandlerController errorHandlerController = new ErrorHandlerController();

        ArrayList<FieldError> fieldErrorList = new ArrayList<>();
        fieldErrorList.add( new FieldError( "errorMessage", "errorMessage", "errorMessage" ) );
        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( fieldErrorList );
        BindingResultException ex = new BindingResultException(
                new BindException( new BindException( new BindException( new BindException( new BindException(
                        new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) ) ) ) ) ) );
        ResponseEntity<ExceptionResponse> actualHandleBindingResultExceptionResult = errorHandlerController
                .handleBindingResultException( ex, new ServletWebRequest( new MockHttpServletRequest() ) );
        assertTrue( actualHandleBindingResultExceptionResult.hasBody() );
        assertTrue( actualHandleBindingResultExceptionResult.getHeaders().isEmpty() );
        assertEquals( HttpStatus.BAD_REQUEST, actualHandleBindingResultExceptionResult.getStatusCode() );
        ExceptionResponse body = actualHandleBindingResultExceptionResult.getBody();
        assertEquals( "Bad Request", body.getMessage() );
        Map<String, Object> details = body.getDetails();
        assertEquals( 3, details.size() );
        assertEquals( 4, ((List<HashMap>) details.get( "errorFields" )).get( 0 ).size() );
        verify( bindingResult ).getFieldErrors();
    }

    /**
     * Method under test: {@link ErrorHandlerController#handleBindingResultException(BindingResultException, WebRequest)}
     */
    @Test
    void testHandleBindingResultException3() {

        ErrorHandlerController errorHandlerController = new ErrorHandlerController();

        ArrayList<FieldError> fieldErrorList = new ArrayList<>();
        fieldErrorList.add( new FieldError( "message", "message", "message" ) );
        fieldErrorList.add( new FieldError( "errorMessage", "errorMessage", "errorMessage" ) );
        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( fieldErrorList );
        BindingResultException ex = new BindingResultException(
                new BindException( new BindException( new BindException( new BindException( new BindException(
                        new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) ) ) ) ) ) );
        ResponseEntity<ExceptionResponse> actualHandleBindingResultExceptionResult = errorHandlerController
                .handleBindingResultException( ex, new ServletWebRequest( new MockHttpServletRequest() ) );
        assertTrue( actualHandleBindingResultExceptionResult.hasBody() );
        assertTrue( actualHandleBindingResultExceptionResult.getHeaders().isEmpty() );
        assertEquals( HttpStatus.BAD_REQUEST, actualHandleBindingResultExceptionResult.getStatusCode() );
        ExceptionResponse body = actualHandleBindingResultExceptionResult.getBody();
        assertEquals( "Bad Request", body.getMessage() );
        Map<String, Object> details = body.getDetails();
        assertEquals( 3, details.size() );
        Object getResult = details.get( "errorFields" );
        assertEquals( 4, ((List<HashMap>) getResult).get( 1 ).size() );
        assertEquals( 4, ((List<HashMap>) getResult).get( 0 ).size() );
        verify( bindingResult ).getFieldErrors();
    }

    /**
     * Method under test: {@link ErrorHandlerController#handleBindingResultException(BindingResultException, WebRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleBindingResultException4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "ar.com.saile.exceptions.BindingResultException.getFieldErrors()" because "ex" is null
        //       at ar.com.saile.controllers.ErrorHandlerController.handleBindingResultException(ErrorHandlerController.java:36)
        //   In order to prevent handleBindingResultException(BindingResultException, WebRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   handleBindingResultException(BindingResultException, WebRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        ErrorHandlerController errorHandlerController = new ErrorHandlerController();
        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( new ArrayList<>() );
        new BindException( new BindException( new BindException( new BindException( new BindException(
                new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) ) ) ) ) );
        errorHandlerController.handleBindingResultException( null, new ServletWebRequest( new MockHttpServletRequest() ) );
    }
}

