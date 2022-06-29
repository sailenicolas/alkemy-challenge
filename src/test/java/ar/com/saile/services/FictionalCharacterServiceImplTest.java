package ar.com.saile.services;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.saile.component.FictionalPathTypeVariable;
import ar.com.saile.domain.FictionalCharacter;
import ar.com.saile.domain.MotionPicture;
import ar.com.saile.exceptions.NotDeletedException;
import ar.com.saile.exceptions.RecordNotFoundException;
import ar.com.saile.repositories.CharacterRepository;
import ar.com.saile.repositories.MotionPictureRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
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

@ContextConfiguration(classes = {FictionalCharacterServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FictionalCharacterServiceImplTest {

    @MockBean
    private CharacterRepository characterRepository;

    @Autowired
    private FictionalCharacterServiceImpl fictionalCharacterServiceImpl;

    @MockBean
    private MotionPictureRepository motionPictureRepository;

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#CreateByMotioPictureIdAndCharacterId(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testCreateByMotioPictureIdAndCharacterId() throws RecordNotFoundException {

        assertTrue(
                fictionalCharacterServiceImpl.CreateByMotioPictureIdAndCharacterId( FictionalPathTypeVariable.UNKOWN, 123L, 1L )
                        .isEmpty() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#CreateByMotioPictureIdAndCharacterId(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testCreateByMotioPictureIdAndCharacterId2() throws RecordNotFoundException {

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        ArrayList<FictionalCharacter> fictionalCharacterList = new ArrayList<>();
        motionPicture.setFictionalCharacters( fictionalCharacterList );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        Optional<MotionPicture> ofResult = Optional.of( motionPicture );
        when( motionPictureRepository.findById( (Long) any() ) ).thenReturn( ofResult );
        Collection<FictionalCharacter> actualCreateByMotioPictureIdAndCharacterIdResult = fictionalCharacterServiceImpl
                .CreateByMotioPictureIdAndCharacterId( FictionalPathTypeVariable.MOVIES, 123L, 1L );
        assertSame( fictionalCharacterList, actualCreateByMotioPictureIdAndCharacterIdResult );
        assertTrue( actualCreateByMotioPictureIdAndCharacterIdResult.isEmpty() );
        verify( motionPictureRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#CreateByMotioPictureIdAndCharacterId(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testCreateByMotioPictureIdAndCharacterId3() throws RecordNotFoundException {

        when( motionPictureRepository.findById( (Long) any() ) ).thenReturn( Optional.empty() );
        assertThrows( RecordNotFoundException.class, () -> fictionalCharacterServiceImpl
                .CreateByMotioPictureIdAndCharacterId( FictionalPathTypeVariable.MOVIES, 123L, 1L ) );
        verify( motionPictureRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#CreateByMotioPictureIdAndCharacterId(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testCreateByMotioPictureIdAndCharacterId4() throws RecordNotFoundException {

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        ArrayList<FictionalCharacter> fictionalCharacterList = new ArrayList<>();
        motionPicture.setFictionalCharacters( fictionalCharacterList );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        Optional<MotionPicture> ofResult = Optional.of( motionPicture );
        when( motionPictureRepository.findById( (Long) any() ) ).thenReturn( ofResult );
        Collection<FictionalCharacter> actualCreateByMotioPictureIdAndCharacterIdResult = fictionalCharacterServiceImpl
                .CreateByMotioPictureIdAndCharacterId( FictionalPathTypeVariable.SERIES, 123L, 1L );
        assertSame( fictionalCharacterList, actualCreateByMotioPictureIdAndCharacterIdResult );
        assertTrue( actualCreateByMotioPictureIdAndCharacterIdResult.isEmpty() );
        verify( motionPictureRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#CreateByMotioPictureIdAndCharacterId(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testCreateByMotioPictureIdAndCharacterId5() throws RecordNotFoundException {

        when( motionPictureRepository.findById( (Long) any() ) ).thenThrow( new NotDeletedException( "An error occurred" ) );
        assertThrows( NotDeletedException.class, () -> fictionalCharacterServiceImpl
                .CreateByMotioPictureIdAndCharacterId( FictionalPathTypeVariable.MOVIES, 123L, 1L ) );
        verify( motionPictureRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#createFictionalByType(FictionalPathTypeVariable, FictionalCharacter)}
     */
    @Test
    void testCreateFictionalByType() {

        FictionalCharacter fictionalCharacter = new FictionalCharacter();
        fictionalCharacter.setAge( 1 );
        fictionalCharacter.setHistory( "History" );
        fictionalCharacter.setId( 123L );
        fictionalCharacter.setName( "Name" );
        fictionalCharacter.setSeries( new ArrayList<>() );
        fictionalCharacter.setWeigh( 1 );
        assertNull(
                fictionalCharacterServiceImpl.createFictionalByType( FictionalPathTypeVariable.UNKOWN, fictionalCharacter ) );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#createFictionalByType(FictionalPathTypeVariable, FictionalCharacter)}
     */
    @Test
    void testCreateFictionalByType2() {

        FictionalCharacter fictionalCharacter = new FictionalCharacter();
        fictionalCharacter.setAge( 1 );
        fictionalCharacter.setHistory( "History" );
        fictionalCharacter.setId( 123L );
        fictionalCharacter.setName( "Name" );
        fictionalCharacter.setSeries( new ArrayList<>() );
        fictionalCharacter.setWeigh( 1 );
        assertNull(
                fictionalCharacterServiceImpl.createFictionalByType( FictionalPathTypeVariable.MOVIES, fictionalCharacter ) );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#createFictionalByType(FictionalPathTypeVariable, FictionalCharacter)}
     */
    @Test
    void testCreateFictionalByType3() {

        FictionalCharacter fictionalCharacter = new FictionalCharacter();
        fictionalCharacter.setAge( 1 );
        fictionalCharacter.setHistory( "History" );
        fictionalCharacter.setId( 123L );
        fictionalCharacter.setName( "Name" );
        fictionalCharacter.setSeries( new ArrayList<>() );
        fictionalCharacter.setWeigh( 1 );
        assertNull(
                fictionalCharacterServiceImpl.createFictionalByType( FictionalPathTypeVariable.SERIES, fictionalCharacter ) );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#createFictionalByType(FictionalPathTypeVariable, FictionalCharacter)}
     */
    @Test
    void testCreateFictionalByType4() {

        FictionalCharacter fictionalCharacter = mock( FictionalCharacter.class );
        doNothing().when( fictionalCharacter ).setAge( (Integer) any() );
        doNothing().when( fictionalCharacter ).setHistory( (String) any() );
        doNothing().when( fictionalCharacter ).setId( (Long) any() );
        doNothing().when( fictionalCharacter ).setName( (String) any() );
        doNothing().when( fictionalCharacter ).setSeries( (Collection<MotionPicture>) any() );
        doNothing().when( fictionalCharacter ).setWeigh( (Integer) any() );
        fictionalCharacter.setAge( 1 );
        fictionalCharacter.setHistory( "History" );
        fictionalCharacter.setId( 123L );
        fictionalCharacter.setName( "Name" );
        fictionalCharacter.setSeries( new ArrayList<>() );
        fictionalCharacter.setWeigh( 1 );
        assertNull(
                fictionalCharacterServiceImpl.createFictionalByType( FictionalPathTypeVariable.UNKOWN, fictionalCharacter ) );
        verify( fictionalCharacter ).setAge( (Integer) any() );
        verify( fictionalCharacter ).setHistory( (String) any() );
        verify( fictionalCharacter ).setId( (Long) any() );
        verify( fictionalCharacter ).setName( (String) any() );
        verify( fictionalCharacter ).setSeries( (Collection<MotionPicture>) any() );
        verify( fictionalCharacter ).setWeigh( (Integer) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#updateFictional(Long, FictionalCharacter)}
     */
    @Test
    void testUpdateFictional() {

        FictionalCharacter fictionalCharacter = new FictionalCharacter();
        fictionalCharacter.setAge( 1 );
        fictionalCharacter.setHistory( "History" );
        fictionalCharacter.setId( 123L );
        fictionalCharacter.setName( "Name" );
        fictionalCharacter.setSeries( new ArrayList<>() );
        fictionalCharacter.setWeigh( 1 );
        assertNull( fictionalCharacterServiceImpl.updateFictional( 123L, fictionalCharacter ) );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#updateFictional(Long, FictionalCharacter)}
     */
    @Test
    void testUpdateFictional2() {

        FictionalCharacter fictionalCharacter = mock( FictionalCharacter.class );
        doNothing().when( fictionalCharacter ).setAge( (Integer) any() );
        doNothing().when( fictionalCharacter ).setHistory( (String) any() );
        doNothing().when( fictionalCharacter ).setId( (Long) any() );
        doNothing().when( fictionalCharacter ).setName( (String) any() );
        doNothing().when( fictionalCharacter ).setSeries( (Collection<MotionPicture>) any() );
        doNothing().when( fictionalCharacter ).setWeigh( (Integer) any() );
        fictionalCharacter.setAge( 1 );
        fictionalCharacter.setHistory( "History" );
        fictionalCharacter.setId( 123L );
        fictionalCharacter.setName( "Name" );
        fictionalCharacter.setSeries( new ArrayList<>() );
        fictionalCharacter.setWeigh( 1 );
        assertNull( fictionalCharacterServiceImpl.updateFictional( 123L, fictionalCharacter ) );
        verify( fictionalCharacter ).setAge( (Integer) any() );
        verify( fictionalCharacter ).setHistory( (String) any() );
        verify( fictionalCharacter ).setId( (Long) any() );
        verify( fictionalCharacter ).setName( (String) any() );
        verify( fictionalCharacter ).setSeries( (Collection<MotionPicture>) any() );
        verify( fictionalCharacter ).setWeigh( (Integer) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#deleteById(Long)}
     */
    @Test
    void testDeleteById() {

        when( characterRepository.existsById( (Long) any() ) ).thenReturn( true );
        doNothing().when( characterRepository ).deleteById( (Long) any() );
        assertThrows( NotDeletedException.class, () -> fictionalCharacterServiceImpl.deleteById( 123L ) );
        verify( characterRepository ).existsById( (Long) any() );
        verify( characterRepository ).deleteById( (Long) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#deleteById(Long)}
     */
    @Test
    void testDeleteById2() {

        when( characterRepository.existsById( (Long) any() ) ).thenReturn( false );
        doNothing().when( characterRepository ).deleteById( (Long) any() );
        fictionalCharacterServiceImpl.deleteById( 123L );
        verify( characterRepository ).existsById( (Long) any() );
        verify( characterRepository ).deleteById( (Long) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#deleteById(Long)}
     */
    @Test
    void testDeleteById3() {

        when( characterRepository.existsById( (Long) any() ) ).thenThrow( new RecordNotFoundException( "An error occurred" ) );
        doThrow( new RecordNotFoundException( "An error occurred" ) ).when( characterRepository ).deleteById( (Long) any() );
        assertThrows( RecordNotFoundException.class, () -> fictionalCharacterServiceImpl.deleteById( 123L ) );
        verify( characterRepository ).deleteById( (Long) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#createFictional(FictionalCharacter)}
     */
    @Test
    void testCreateFictional() {

        FictionalCharacter fictionalCharacter = new FictionalCharacter();
        fictionalCharacter.setAge( 1 );
        fictionalCharacter.setHistory( "History" );
        fictionalCharacter.setId( 123L );
        fictionalCharacter.setName( "Name" );
        fictionalCharacter.setSeries( new ArrayList<>() );
        fictionalCharacter.setWeigh( 1 );
        when( characterRepository.save( (FictionalCharacter) any() ) ).thenReturn( fictionalCharacter );

        FictionalCharacter fictionalCharacter1 = new FictionalCharacter();
        fictionalCharacter1.setAge( 1 );
        fictionalCharacter1.setHistory( "History" );
        fictionalCharacter1.setId( 123L );
        fictionalCharacter1.setName( "Name" );
        fictionalCharacter1.setSeries( new ArrayList<>() );
        fictionalCharacter1.setWeigh( 1 );
        assertSame( fictionalCharacter, fictionalCharacterServiceImpl.createFictional( fictionalCharacter1 ) );
        verify( characterRepository ).save( (FictionalCharacter) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#createFictional(FictionalCharacter)}
     */
    @Test
    void testCreateFictional2() {

        when( characterRepository.save( (FictionalCharacter) any() ) ).thenThrow( new NotDeletedException( "An error occurred" ) );

        FictionalCharacter fictionalCharacter = new FictionalCharacter();
        fictionalCharacter.setAge( 1 );
        fictionalCharacter.setHistory( "History" );
        fictionalCharacter.setId( 123L );
        fictionalCharacter.setName( "Name" );
        fictionalCharacter.setSeries( new ArrayList<>() );
        fictionalCharacter.setWeigh( 1 );
        assertThrows( NotDeletedException.class, () -> fictionalCharacterServiceImpl.createFictional( fictionalCharacter ) );
        verify( characterRepository ).save( (FictionalCharacter) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#deleteByMotionPictureIdAndCharacterId(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteByMotionPictureIdAndCharacterId() {

        assertNull(
                fictionalCharacterServiceImpl.deleteByMotionPictureIdAndCharacterId( FictionalPathTypeVariable.UNKOWN, 1L, 1L ) );
        assertNull(
                fictionalCharacterServiceImpl.deleteByMotionPictureIdAndCharacterId( FictionalPathTypeVariable.MOVIES, 1L, 1L ) );
        assertNull(
                fictionalCharacterServiceImpl.deleteByMotionPictureIdAndCharacterId( FictionalPathTypeVariable.SERIES, 1L, 1L ) );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#findById(Long)}
     */
    @Test
    void testFindById() {

        FictionalCharacter fictionalCharacter = new FictionalCharacter();
        fictionalCharacter.setAge( 1 );
        fictionalCharacter.setHistory( "History" );
        fictionalCharacter.setId( 123L );
        fictionalCharacter.setName( "Name" );
        fictionalCharacter.setSeries( new ArrayList<>() );
        fictionalCharacter.setWeigh( 1 );
        Optional<FictionalCharacter> ofResult = Optional.of( fictionalCharacter );
        when( characterRepository.findById( (Long) any() ) ).thenReturn( ofResult );
        assertSame( fictionalCharacter, fictionalCharacterServiceImpl.findById( 123L ) );
        verify( characterRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#findById(Long)}
     */
    @Test
    void testFindById2() {

        when( characterRepository.findById( (Long) any() ) ).thenReturn( Optional.empty() );
        assertThrows( RecordNotFoundException.class, () -> fictionalCharacterServiceImpl.findById( 123L ) );
        verify( characterRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#findById(Long)}
     */
    @Test
    void testFindById3() {

        when( characterRepository.findById( (Long) any() ) ).thenThrow( new NotDeletedException( "An error occurred" ) );
        assertThrows( NotDeletedException.class, () -> fictionalCharacterServiceImpl.findById( 123L ) );
        verify( characterRepository ).findById( (Long) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#findAll(int, String)}
     */
    @Test
    void testFindAll() {

        PageImpl<FictionalCharacter> pageImpl = new PageImpl<>( new ArrayList<>() );
        when( characterRepository.findAll( (Pageable) any() ) ).thenReturn( pageImpl );
        Page<FictionalCharacter> actualFindAllResult = fictionalCharacterServiceImpl.findAll( 1, "Order" );
        assertSame( pageImpl, actualFindAllResult );
        assertTrue( actualFindAllResult.toList().isEmpty() );
        verify( characterRepository ).findAll( (Pageable) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#findAll(int, String)}
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
        //       at ar.com.saile.services.FictionalCharacterServiceImpl.findAll(FictionalCharacterServiceImpl.java:88)
        //   In order to prevent findAll(int, String)
        //   from throwing IllegalArgumentException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   findAll(int, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when( characterRepository.findAll( (Pageable) any() ) ).thenReturn( new PageImpl<>( new ArrayList<>() ) );
        fictionalCharacterServiceImpl.findAll( -1, "Order" );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#findAll(int, String)}
     */
    @Test
    void testFindAll3() {

        PageImpl<FictionalCharacter> pageImpl = new PageImpl<>( new ArrayList<>() );
        when( characterRepository.findAll( (Pageable) any() ) ).thenReturn( pageImpl );
        Page<FictionalCharacter> actualFindAllResult = fictionalCharacterServiceImpl.findAll( 1, "desc" );
        assertSame( pageImpl, actualFindAllResult );
        assertTrue( actualFindAllResult.toList().isEmpty() );
        verify( characterRepository ).findAll( (Pageable) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#findAll(int, String)}
     */
    @Test
    void testFindAll4() {

        when( characterRepository.findAll( (Pageable) any() ) ).thenThrow( new NotDeletedException( "An error occurred" ) );
        assertThrows( NotDeletedException.class, () -> fictionalCharacterServiceImpl.findAll( 1, "Order" ) );
        verify( characterRepository ).findAll( (Pageable) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#findAll(int, Map)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAll5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equalsIgnoreCase(String)" because "order" is null
        //       at ar.com.saile.component.Utils.getPageable(Utils.java:15)
        //       at ar.com.saile.services.FictionalCharacterServiceImpl.findAll(FictionalCharacterServiceImpl.java:77)
        //   In order to prevent findAll(int, Map)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   findAll(int, Map).
        //   See https://diff.blue/R013 to resolve this issue.

        fictionalCharacterServiceImpl.findAll( 1, new HashMap<>() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#findAll(int, Map)}
     */
    @Test
    void testFindAll6() {

        PageImpl<FictionalCharacter> pageImpl = new PageImpl<>( new ArrayList<>() );
        when( characterRepository.findAll( (Specification<FictionalCharacter>) any(), (Pageable) any() ) ).thenReturn( pageImpl );

        HashMap<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put( "order", "42" );
        Page<FictionalCharacter> actualFindAllResult = fictionalCharacterServiceImpl.findAll( 1, stringStringMap );
        assertSame( pageImpl, actualFindAllResult );
        assertTrue( actualFindAllResult.toList().isEmpty() );
        verify( characterRepository ).findAll( (Specification<FictionalCharacter>) any(), (Pageable) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#findAll(int, Map)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAll7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero!
        //       at org.springframework.data.domain.AbstractPageRequest.<init>(AbstractPageRequest.java:44)
        //       at org.springframework.data.domain.PageRequest.<init>(PageRequest.java:45)
        //       at org.springframework.data.domain.PageRequest.of(PageRequest.java:72)
        //       at ar.com.saile.component.Utils.getPageable(Utils.java:17)
        //       at ar.com.saile.services.FictionalCharacterServiceImpl.findAll(FictionalCharacterServiceImpl.java:77)
        //   In order to prevent findAll(int, Map)
        //   from throwing IllegalArgumentException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   findAll(int, Map).
        //   See https://diff.blue/R013 to resolve this issue.

        when( characterRepository.findAll( (Specification<FictionalCharacter>) any(), (Pageable) any() ) )
                .thenReturn( new PageImpl<>( new ArrayList<>() ) );

        HashMap<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put( "order", "42" );
        fictionalCharacterServiceImpl.findAll( -1, stringStringMap );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#findAll(int, Map)}
     */
    @Test
    void testFindAll8() {

        PageImpl<FictionalCharacter> pageImpl = new PageImpl<>( new ArrayList<>() );
        when( characterRepository.findAll( (Specification<FictionalCharacter>) any(), (Pageable) any() ) ).thenReturn( pageImpl );

        HashMap<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put( "order", "desc" );
        Page<FictionalCharacter> actualFindAllResult = fictionalCharacterServiceImpl.findAll( 1, stringStringMap );
        assertSame( pageImpl, actualFindAllResult );
        assertTrue( actualFindAllResult.toList().isEmpty() );
        verify( characterRepository ).findAll( (Specification<FictionalCharacter>) any(), (Pageable) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#findAll(int, Map)}
     */
    @Test
    void testFindAll9() {

        when( characterRepository.findAll( (Specification<FictionalCharacter>) any(), (Pageable) any() ) )
                .thenThrow( new NotDeletedException( "An error occurred" ) );

        HashMap<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put( "order", "42" );
        assertThrows( NotDeletedException.class, () -> fictionalCharacterServiceImpl.findAll( 1, stringStringMap ) );
        verify( characterRepository ).findAll( (Specification<FictionalCharacter>) any(), (Pageable) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#searchAll(int, Map)}
     */
    @Test
    void testSearchAll() {

        ArrayList<FictionalCharacter> fictionalCharacterList = new ArrayList<>();
        when( characterRepository.findAll( (Specification<FictionalCharacter>) any() ) ).thenReturn( fictionalCharacterList );
        List<FictionalCharacter> actualSearchAllResult = fictionalCharacterServiceImpl.searchAll( 1, new HashMap<>() );
        assertSame( fictionalCharacterList, actualSearchAllResult );
        assertTrue( actualSearchAllResult.isEmpty() );
        verify( characterRepository ).findAll( (Specification<FictionalCharacter>) any() );
    }

    /**
     * Method under test: {@link FictionalCharacterServiceImpl#searchAll(int, Map)}
     */
    @Test
    void testSearchAll2() {

        when( characterRepository.findAll( (Specification<FictionalCharacter>) any() ) )
                .thenThrow( new NotDeletedException( "An error occurred" ) );
        assertThrows( NotDeletedException.class, () -> fictionalCharacterServiceImpl.searchAll( 1, new HashMap<>() ) );
        verify( characterRepository ).findAll( (Specification<FictionalCharacter>) any() );
    }
}

