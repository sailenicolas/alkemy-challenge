package ar.com.saile.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.saile.domain.Genre;
import ar.com.saile.exceptions.NotDeletedException;
import ar.com.saile.exceptions.RecordNotFoundException;
import ar.com.saile.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GenreServiceImpl.class})
@ExtendWith(SpringExtension.class)
class GenreServiceImplTest {

    @MockBean
    private GenreRepository genreRepository;

    @Autowired
    private GenreServiceImpl genreServiceImpl;

    /**
     * Method under test: {@link GenreServiceImpl#findAll(int, String)}
     */
    @Test
    void testFindAll() {

        PageImpl<Genre> pageImpl = new PageImpl<>( new ArrayList<>() );
        when( genreRepository.findAll( (Pageable) any() ) ).thenReturn( pageImpl );
        Page<Genre> actualFindAllResult = genreServiceImpl.findAll( 1, "Order" );
        assertSame( pageImpl, actualFindAllResult );
        assertTrue( actualFindAllResult.toList().isEmpty() );
        verify( genreRepository ).findAll( (Pageable) any() );
    }

    /**
     * Method under test: {@link GenreServiceImpl#findAll(int, String)}
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
        //       at ar.com.saile.component.Utils.getPageable(Utils.java:18)
        //       at ar.com.saile.services.GenreServiceImpl.findAll(GenreServiceImpl.java:20)
        //   In order to prevent findAll(int, String)
        //   from throwing IllegalArgumentException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   findAll(int, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when( genreRepository.findAll( (Pageable) any() ) ).thenReturn( new PageImpl<>( new ArrayList<>() ) );
        genreServiceImpl.findAll( -1, "Order" );
    }

    /**
     * Method under test: {@link GenreServiceImpl#findAll(int, String)}
     */
    @Test
    void testFindAll3() {

        PageImpl<Genre> pageImpl = new PageImpl<>( new ArrayList<>() );
        when( genreRepository.findAll( (Pageable) any() ) ).thenReturn( pageImpl );
        Page<Genre> actualFindAllResult = genreServiceImpl.findAll( 1, "desc" );
        assertSame( pageImpl, actualFindAllResult );
        assertTrue( actualFindAllResult.toList().isEmpty() );
        verify( genreRepository ).findAll( (Pageable) any() );
    }

    /**
     * Method under test: {@link GenreServiceImpl#findAll(int, String)}
     */
    @Test
    void testFindAll4() {

        when( genreRepository.findAll( (Pageable) any() ) ).thenThrow( new NotDeletedException( "An error occurred" ) );
        assertThrows( NotDeletedException.class, () -> genreServiceImpl.findAll( 1, "Order" ) );
        verify( genreRepository ).findAll( (Pageable) any() );
    }

    /**
     * Method under test: {@link GenreServiceImpl#findById(Long)}
     */
    @Test
    void testFindById() {

        Genre genre = new Genre();
        genre.setImage( "Image" );
        genre.setMotionPictures( new ArrayList<>() );
        genre.setName( "Name" );
        Optional<Genre> ofResult = Optional.of( genre );
        when( genreRepository.findById( (Long) any() ) ).thenReturn( ofResult );
        assertSame( genre, genreServiceImpl.findById( 123L ) );
        verify( genreRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link GenreServiceImpl#findById(Long)}
     */
    @Test
    void testFindById2() {

        when( genreRepository.findById( (Long) any() ) ).thenReturn( Optional.empty() );
        assertThrows( RecordNotFoundException.class, () -> genreServiceImpl.findById( 123L ) );
        verify( genreRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link GenreServiceImpl#findById(Long)}
     */
    @Test
    void testFindById3() {

        when( genreRepository.findById( (Long) any() ) ).thenThrow( new NotDeletedException( "An error occurred" ) );
        assertThrows( NotDeletedException.class, () -> genreServiceImpl.findById( 123L ) );
        verify( genreRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link GenreServiceImpl#updateMovie(Long, Genre)}
     */
    @Test
    void testUpdateMovie() {

        Genre genre = new Genre();
        genre.setImage( "Image" );
        genre.setMotionPictures( new ArrayList<>() );
        genre.setName( "Name" );
        when( genreRepository.save( (Genre) any() ) ).thenReturn( genre );

        Genre genre1 = new Genre();
        genre1.setImage( "Image" );
        genre1.setMotionPictures( new ArrayList<>() );
        genre1.setName( "Name" );
        assertSame( genre, genreServiceImpl.updateMovie( 123L, genre1 ) );
        verify( genreRepository ).save( (Genre) any() );
    }

    /**
     * Method under test: {@link GenreServiceImpl#updateMovie(Long, Genre)}
     */
    @Test
    void testUpdateMovie2() {

        when( genreRepository.save( (Genre) any() ) ).thenThrow( new NotDeletedException( "An error occurred" ) );

        Genre genre = new Genre();
        genre.setImage( "Image" );
        genre.setMotionPictures( new ArrayList<>() );
        genre.setName( "Name" );
        assertThrows( NotDeletedException.class, () -> genreServiceImpl.updateMovie( 123L, genre ) );
        verify( genreRepository ).save( (Genre) any() );
    }

    /**
     * Method under test: {@link GenreServiceImpl#deleteById(Long)}
     */
    @Test
    void testDeleteById() {

        when( genreRepository.existsById( (Long) any() ) ).thenReturn( true );
        doNothing().when( genreRepository ).deleteById( (Long) any() );
        assertThrows( NotDeletedException.class, () -> genreServiceImpl.deleteById( 123L ) );
        verify( genreRepository ).existsById( (Long) any() );
        verify( genreRepository ).deleteById( (Long) any() );
    }

    /**
     * Method under test: {@link GenreServiceImpl#deleteById(Long)}
     */
    @Test
    void testDeleteById2() {

        when( genreRepository.existsById( (Long) any() ) ).thenReturn( false );
        doNothing().when( genreRepository ).deleteById( (Long) any() );
        genreServiceImpl.deleteById( 123L );
        verify( genreRepository ).existsById( (Long) any() );
        verify( genreRepository ).deleteById( (Long) any() );
    }

    /**
     * Method under test: {@link GenreServiceImpl#deleteById(Long)}
     */
    @Test
    void testDeleteById3() {

        when( genreRepository.existsById( (Long) any() ) ).thenThrow( new RecordNotFoundException( "An error occurred" ) );
        doThrow( new RecordNotFoundException( "An error occurred" ) ).when( genreRepository ).deleteById( (Long) any() );
        assertThrows( RecordNotFoundException.class, () -> genreServiceImpl.deleteById( 123L ) );
        verify( genreRepository ).deleteById( (Long) any() );
    }

    /**
     * Method under test: {@link GenreServiceImpl#createMovie(Genre)}
     */
    @Test
    void testCreateMovie() {

        Genre genre = new Genre();
        genre.setImage( "Image" );
        genre.setMotionPictures( new ArrayList<>() );
        genre.setName( "Name" );
        when( genreRepository.save( (Genre) any() ) ).thenReturn( genre );

        Genre genre1 = new Genre();
        genre1.setImage( "Image" );
        genre1.setMotionPictures( new ArrayList<>() );
        genre1.setName( "Name" );
        assertSame( genre, genreServiceImpl.createMovie( genre1 ) );
        verify( genreRepository ).save( (Genre) any() );
    }

    /**
     * Method under test: {@link GenreServiceImpl#createMovie(Genre)}
     */
    @Test
    void testCreateMovie2() {

        when( genreRepository.save( (Genre) any() ) ).thenThrow( new NotDeletedException( "An error occurred" ) );

        Genre genre = new Genre();
        genre.setImage( "Image" );
        genre.setMotionPictures( new ArrayList<>() );
        genre.setName( "Name" );
        assertThrows( NotDeletedException.class, () -> genreServiceImpl.createMovie( genre ) );
        verify( genreRepository ).save( (Genre) any() );
    }

    /**
     * Method under test: {@link GenreServiceImpl#save(Genre)}
     */
    @Test
    void testSave() {

        Genre genre = new Genre();
        genre.setImage( "Image" );
        genre.setMotionPictures( new ArrayList<>() );
        genre.setName( "Name" );
        when( genreRepository.save( (Genre) any() ) ).thenReturn( genre );

        Genre genre1 = new Genre();
        genre1.setImage( "Image" );
        genre1.setMotionPictures( new ArrayList<>() );
        genre1.setName( "Name" );
        assertSame( genre, genreServiceImpl.save( genre1 ) );
        verify( genreRepository ).save( (Genre) any() );
    }

    /**
     * Method under test: {@link GenreServiceImpl#save(Genre)}
     */
    @Test
    void testSave2() {

        when( genreRepository.save( (Genre) any() ) ).thenThrow( new NotDeletedException( "An error occurred" ) );

        Genre genre = new Genre();
        genre.setImage( "Image" );
        genre.setMotionPictures( new ArrayList<>() );
        genre.setName( "Name" );
        assertThrows( NotDeletedException.class, () -> genreServiceImpl.save( genre ) );
        verify( genreRepository ).save( (Genre) any() );
    }
}

