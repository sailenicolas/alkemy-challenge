package ar.com.saile.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RegisterFailedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RegisterFailedException(String message) {

        super( message );
    }

    public final static class UsernameAlreadyTakenException extends RegisterFailedException {

        public UsernameAlreadyTakenException(String message) {

            super( message );
        }
    }
}