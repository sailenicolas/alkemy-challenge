package ar.com.saile.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class RecordNotFoundExceptionTest {

    /**
     * Method under test: {@link RecordNotFoundException#RecordNotFoundException(String)}
     */
    @Test
    void testConstructor() {

        RecordNotFoundException actualRecordNotFoundException = new RecordNotFoundException( "An error occurred" );
        assertNull( actualRecordNotFoundException.getCause() );
        assertEquals( 0, actualRecordNotFoundException.getSuppressed().length );
        assertEquals( "An error occurred", actualRecordNotFoundException.getMessage() );
        assertEquals( "An error occurred", actualRecordNotFoundException.getLocalizedMessage() );
    }
}

