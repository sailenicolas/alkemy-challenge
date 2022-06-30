package ar.com.saile.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginFailedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public LoginFailedException(String message) {

        super(message);
    }
    public static final class UserNotActivatedException extends LoginFailedException{

        public UserNotActivatedException(String message) {

            super( message );
        }
    }
}

