package ar.com.saile.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.saile.domain.MotionPicture;
import ar.com.saile.repositories.MotionPictureRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MotionPictureServiceImpl.class})
@ExtendWith(SpringExtension.class)
class MotionPictureServiceImplTest {

    @MockBean
    private MotionPictureRepository motionPictureRepository;

    @Autowired
    private MotionPictureServiceImpl motionPictureServiceImpl;

    /**
     * Method under test: {@link MotionPictureServiceImpl#findAll(Integer, String)}
     */
    @Test
    void testFindAll() {

        PageImpl<MotionPicture> pageImpl = new PageImpl<>( new ArrayList<>() );
        when( motionPictureRepository.findAll( (Pageable) any() ) ).thenReturn( pageImpl );
        Page<MotionPicture> actualFindAllResult = motionPictureServiceImpl.findAll( 1, "Order" );
        assertSame( pageImpl, actualFindAllResult );
        assertTrue( actualFindAllResult.toList().isEmpty() );
        verify( motionPictureRepository ).findAll( (Pageable) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#findAll(Integer, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAll2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero!
        //       at org.springframework.data.domain.AbstractPageRequest.<init>(AbstractPageRequest.java:44)
        //       at org.springframework.data.domain.PageRequest.<init>(PageRequest.java:45)
        //       at org.springframework.data.domain.PageRequest.of(PageRequest.java:72)
        //       at ar.com.saile.component.Utils.getPageable(Utils.java:17)
        //       at ar.com.saile.services.MotionPictureServiceImpl.findAll(MotionPictureServiceImpl.java:24)
        //   In order to prevent findAll(Integer, String)
        //   from throwing IllegalArgumentException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   findAll(Integer, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when( motionPictureRepository.findAll( (Pageable) any() ) ).thenReturn( new PageImpl<>( new ArrayList<>() ) );
        motionPictureServiceImpl.findAll( -1, "Order" );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#findAll(Integer, String)}
     */
    @Test
    void testFindAll3() {

        PageImpl<MotionPicture> pageImpl = new PageImpl<>( new ArrayList<>() );
        when( motionPictureRepository.findAll( (Pageable) any() ) ).thenReturn( pageImpl );
        Page<MotionPicture> actualFindAllResult = motionPictureServiceImpl.findAll( 1, "desc" );
        assertSame( pageImpl, actualFindAllResult );
        assertTrue( actualFindAllResult.toList().isEmpty() );
        verify( motionPictureRepository ).findAll( (Pageable) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#findAll(Integer, String)}
     */
    @Test
    void testFindAll4() {

        when( motionPictureRepository.findAll( (Pageable) any() ) ).thenThrow( new NotDeletedException( "An error occurred" ) );
        assertThrows( NotDeletedException.class, () -> motionPictureServiceImpl.findAll( 1, "Order" ) );
        verify( motionPictureRepository ).findAll( (Pageable) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#findAll(Integer, Map)}
     */
    @Test
    void testFindAll5() {

        ArrayList<MotionPicture> motionPictureList = new ArrayList<>();
        when( motionPictureRepository.findAll( (Specification<MotionPicture>) any() ) ).thenReturn( motionPictureList );
        List<MotionPicture> actualFindAllResult = motionPictureServiceImpl.findAll( 1, new HashMap<>() );
        assertSame( motionPictureList, actualFindAllResult );
        assertTrue( actualFindAllResult.isEmpty() );
        verify( motionPictureRepository ).findAll( (Specification<MotionPicture>) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#findAll(Integer, Map)}
     */
    @Test
    void testFindAll6() {

        when( motionPictureRepository.findAll( (Specification<MotionPicture>) any() ) )
                .thenThrow( new NotDeletedException( "An error occurred" ) );
        assertThrows( NotDeletedException.class, () -> motionPictureServiceImpl.findAll( 1, new HashMap<>() ) );
        verify( motionPictureRepository ).findAll( (Specification<MotionPicture>) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#findById(Long)}
     */
    @Test
    void testFindById() {

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        Optional<MotionPicture> ofResult = Optional.of( motionPicture );
        when( motionPictureRepository.findById( (Long) any() ) ).thenReturn( ofResult );
        assertSame( motionPicture, motionPictureServiceImpl.findById( 123L ) );
        verify( motionPictureRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#findById(Long)}
     */
    @Test
    void testFindById2() {

        when( motionPictureRepository.findById( (Long) any() ) ).thenReturn( Optional.empty() );
        assertThrows( RecordNotFoundException.class, () -> motionPictureServiceImpl.findById( 123L ) );
        verify( motionPictureRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#findById(Long)}
     */
    @Test
    void testFindById3() {

        when( motionPictureRepository.findById( (Long) any() ) ).thenThrow( new NotDeletedException( "An error occurred" ) );
        assertThrows( NotDeletedException.class, () -> motionPictureServiceImpl.findById( 123L ) );
        verify( motionPictureRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#save(MotionPicture)}
     */
    @Test
    void testSave() {

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        when( motionPictureRepository.save( (MotionPicture) any() ) ).thenReturn( motionPicture );

        MotionPicture motionPicture1 = new MotionPicture();
        motionPicture1.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture1.setFictionalCharacters( new ArrayList<>() );
        motionPicture1.setGenres( new ArrayList<>() );
        motionPicture1.setImage( "Image" );
        motionPicture1.setRating( 1 );
        motionPicture1.setTitle( "Dr" );
        assertSame( motionPicture, motionPictureServiceImpl.save( motionPicture1 ) );
        verify( motionPictureRepository ).save( (MotionPicture) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#save(MotionPicture)}
     */
    @Test
    void testSave2() {

        when( motionPictureRepository.save( (MotionPicture) any() ) ).thenThrow( new NotDeletedException( "An error occurred" ) );

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        assertThrows( NotDeletedException.class, () -> motionPictureServiceImpl.save( motionPicture ) );
        verify( motionPictureRepository ).save( (MotionPicture) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#delete(Long)}
     */
    @Test
    void testDelete() {

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        Optional<MotionPicture> ofResult = Optional.of( motionPicture );
        when( motionPictureRepository.existsById( (Long) any() ) ).thenReturn( true );
        doNothing().when( motionPictureRepository ).delete( (MotionPicture) any() );
        when( motionPictureRepository.findById( (Long) any() ) ).thenReturn( ofResult );
        assertThrows( NotDeletedException.class, () -> motionPictureServiceImpl.delete( 123L ) );
        verify( motionPictureRepository ).existsById( (Long) any() );
        verify( motionPictureRepository ).findById( (Long) any() );
        verify( motionPictureRepository ).delete( (MotionPicture) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#delete(Long)}
     */
    @Test
    void testDelete2() {

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        Optional<MotionPicture> ofResult = Optional.of( motionPicture );
        when( motionPictureRepository.existsById( (Long) any() ) ).thenThrow( new RecordNotFoundException( "An error occurred" ) );
        doThrow( new RecordNotFoundException( "An error occurred" ) ).when( motionPictureRepository )
                .delete( (MotionPicture) any() );
        when( motionPictureRepository.findById( (Long) any() ) ).thenReturn( ofResult );
        assertThrows( RecordNotFoundException.class, () -> motionPictureServiceImpl.delete( 123L ) );
        verify( motionPictureRepository ).findById( (Long) any() );
        verify( motionPictureRepository ).delete( (MotionPicture) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#delete(Long)}
     */
    @Test
    void testDelete3() {

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        Optional<MotionPicture> ofResult = Optional.of( motionPicture );
        when( motionPictureRepository.existsById( (Long) any() ) ).thenReturn( false );
        doNothing().when( motionPictureRepository ).delete( (MotionPicture) any() );
        when( motionPictureRepository.findById( (Long) any() ) ).thenReturn( ofResult );
        motionPictureServiceImpl.delete( 123L );
        verify( motionPictureRepository ).existsById( (Long) any() );
        verify( motionPictureRepository ).findById( (Long) any() );
        verify( motionPictureRepository ).delete( (MotionPicture) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#delete(Long)}
     */
    @Test
    void testDelete4() {

        when( motionPictureRepository.existsById( (Long) any() ) ).thenReturn( true );
        doNothing().when( motionPictureRepository ).delete( (MotionPicture) any() );
        when( motionPictureRepository.findById( (Long) any() ) ).thenReturn( Optional.empty() );
        assertThrows( RecordNotFoundException.class, () -> motionPictureServiceImpl.delete( 123L ) );
        verify( motionPictureRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#updateById(Long, MotionPicture)}
     */
    @Test
    void testUpdateById() {

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        Optional<MotionPicture> ofResult = Optional.of( motionPicture );

        MotionPicture motionPicture1 = new MotionPicture();
        motionPicture1.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture1.setFictionalCharacters( new ArrayList<>() );
        motionPicture1.setGenres( new ArrayList<>() );
        motionPicture1.setImage( "Image" );
        motionPicture1.setRating( 1 );
        motionPicture1.setTitle( "Dr" );
        when( motionPictureRepository.save( (MotionPicture) any() ) ).thenReturn( motionPicture1 );
        when( motionPictureRepository.findById( (Long) any() ) ).thenReturn( ofResult );

        MotionPicture motionPicture2 = new MotionPicture();
        motionPicture2.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture2.setFictionalCharacters( new ArrayList<>() );
        motionPicture2.setGenres( new ArrayList<>() );
        motionPicture2.setImage( "Image" );
        motionPicture2.setRating( 1 );
        motionPicture2.setTitle( "Dr" );
        assertSame( motionPicture1, motionPictureServiceImpl.updateById( 123L, motionPicture2 ) );
        verify( motionPictureRepository ).save( (MotionPicture) any() );
        verify( motionPictureRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#updateById(Long, MotionPicture)}
     */
    @Test
    void testUpdateById2() {

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        Optional<MotionPicture> ofResult = Optional.of( motionPicture );
        when( motionPictureRepository.save( (MotionPicture) any() ) ).thenThrow( new NotDeletedException( "An error occurred" ) );
        when( motionPictureRepository.findById( (Long) any() ) ).thenReturn( ofResult );

        MotionPicture motionPicture1 = new MotionPicture();
        motionPicture1.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture1.setFictionalCharacters( new ArrayList<>() );
        motionPicture1.setGenres( new ArrayList<>() );
        motionPicture1.setImage( "Image" );
        motionPicture1.setRating( 1 );
        motionPicture1.setTitle( "Dr" );
        assertThrows( NotDeletedException.class, () -> motionPictureServiceImpl.updateById( 123L, motionPicture1 ) );
        verify( motionPictureRepository ).save( (MotionPicture) any() );
        verify( motionPictureRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link MotionPictureServiceImpl#updateById(Long, MotionPicture)}
     */
    @Test
    void testUpdateById3() {

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        when( motionPictureRepository.save( (MotionPicture) any() ) ).thenReturn( motionPicture );
        when( motionPictureRepository.findById( (Long) any() ) ).thenReturn( Optional.empty() );

        MotionPicture motionPicture1 = new MotionPicture();
        motionPicture1.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture1.setFictionalCharacters( new ArrayList<>() );
        motionPicture1.setGenres( new ArrayList<>() );
        motionPicture1.setImage( "Image" );
        motionPicture1.setRating( 1 );
        motionPicture1.setTitle( "Dr" );
        assertThrows( RecordNotFoundException.class, () -> motionPictureServiceImpl.updateById( 123L, motionPicture1 ) );
        verify( motionPictureRepository ).findById( (Long) any() );
    }
}

