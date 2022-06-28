package ar.com.saile.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotDeletedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NotDeletedException(String message) {

        super(message);
    }
}
