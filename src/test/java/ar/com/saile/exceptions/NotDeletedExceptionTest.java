package ar.com.saile.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class NotDeletedExceptionTest {

    /**
     * Method under test: {@link NotDeletedException#NotDeletedException(String)}
     */
    @Test
    void testConstructor() {

        NotDeletedException actualNotDeletedException = new NotDeletedException( "An error occurred" );
        assertNull( actualNotDeletedException.getCause() );
        assertEquals( 0, actualNotDeletedException.getSuppressed().length );
        assertEquals( "An error occurred", actualNotDeletedException.getMessage() );
        assertEquals( "An error occurred", actualNotDeletedException.getLocalizedMessage() );
    }
}

