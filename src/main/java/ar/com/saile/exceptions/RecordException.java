package ar.com.saile.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RecordException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static final class NotDeletedException extends RuntimeException {

        @Serial
        private static final long serialVersionUID = 1L;

        public NotDeletedException(String message) {

            super(message);
        }
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class RecordNotFoundException extends RuntimeException {

        @Serial
        private static final long serialVersionUID = 1L;

        public RecordNotFoundException(String message) {

            super(message);
        }
    }
}
