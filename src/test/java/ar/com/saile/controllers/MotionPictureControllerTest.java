package ar.com.saile.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.saile.domain.FictionalCharacter;
import ar.com.saile.domain.Genre;
import ar.com.saile.domain.MotionPicture;
import ar.com.saile.exceptions.BindingResultException;
import ar.com.saile.repositories.MotionPictureRepository;
import ar.com.saile.services.MotionPictureService;
import ar.com.saile.services.MotionPictureServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@ContextConfiguration(classes = {MotionPictureController.class})
@ExtendWith(SpringExtension.class)
class MotionPictureControllerTest {

    @Autowired
    private MotionPictureController motionPictureController;

    @MockBean
    private MotionPictureService motionPictureService;

    /**
     * Method under test: {@link MotionPictureController#createMotionPicture(MotionPicture, BindingResult)}
     */
    @Test
    void testCreateMotionPicture() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of MotionPictureController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        MotionPictureRepository motionPictureRepository = mock( MotionPictureRepository.class );
        when( motionPictureRepository.save( (MotionPicture) any() ) ).thenReturn( motionPicture );
        MotionPictureController motionPictureController = new MotionPictureController(
                new MotionPictureServiceImpl( motionPictureRepository ) );
        MotionPicture motionPicture1 = mock( MotionPicture.class );
        doNothing().when( motionPicture1 ).setCreated( (LocalDateTime) any() );
        doNothing().when( motionPicture1 ).setFictionalCharacters( (Collection<FictionalCharacter>) any() );
        doNothing().when( motionPicture1 ).setGenres( (Collection<Genre>) any() );
        doNothing().when( motionPicture1 ).setImage( (String) any() );
        doNothing().when( motionPicture1 ).setRating( (Integer) any() );
        doNothing().when( motionPicture1 ).setTitle( (String) any() );
        motionPicture1.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture1.setFictionalCharacters( new ArrayList<>() );
        motionPicture1.setGenres( new ArrayList<>() );
        motionPicture1.setImage( "Image" );
        motionPicture1.setRating( 1 );
        motionPicture1.setTitle( "Dr" );
        ResponseEntity<?> actualCreateMotionPictureResult = motionPictureController.createMotionPicture( motionPicture1,
                new BindException( new BindException( new BindException(
                        new BindException( new BindException( new BeanPropertyBindingResult( "Target", "Object Name" ) ) ) ) ) ) );
        assertTrue( actualCreateMotionPictureResult.hasBody() );
        assertTrue( actualCreateMotionPictureResult.getHeaders().isEmpty() );
        assertEquals( HttpStatus.OK, actualCreateMotionPictureResult.getStatusCode() );
        verify( motionPictureRepository ).save( (MotionPicture) any() );
        verify( motionPicture1 ).setCreated( (LocalDateTime) any() );
        verify( motionPicture1 ).setFictionalCharacters( (Collection<FictionalCharacter>) any() );
        verify( motionPicture1 ).setGenres( (Collection<Genre>) any() );
        verify( motionPicture1 ).setImage( (String) any() );
        verify( motionPicture1 ).setRating( (Integer) any() );
        verify( motionPicture1 ).setTitle( (String) any() );
    }

    /**
     * Method under test: {@link MotionPictureController#createMotionPicture(MotionPicture, BindingResult)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateMotionPicture2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of MotionPictureController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "ar.com.saile.services.MotionPictureService.save(ar.com.saile.domain.MotionPicture)" because "this.motionPictureService" is null
        //       at ar.com.saile.controllers.MotionPictureController.createMotionPicture(MotionPictureController.java:52)
        //   In order to prevent createMotionPicture(MotionPicture, BindingResult)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createMotionPicture(MotionPicture, BindingResult).
        //   See https://diff.blue/R013 to resolve this issue.

        MotionPictureController motionPictureController = new MotionPictureController( null );
        MotionPicture motionPicture = mock( MotionPicture.class );
        doNothing().when( motionPicture ).setCreated( (LocalDateTime) any() );
        doNothing().when( motionPicture ).setFictionalCharacters( (Collection<FictionalCharacter>) any() );
        doNothing().when( motionPicture ).setGenres( (Collection<Genre>) any() );
        doNothing().when( motionPicture ).setImage( (String) any() );
        doNothing().when( motionPicture ).setRating( (Integer) any() );
        doNothing().when( motionPicture ).setTitle( (String) any() );
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        motionPictureController.createMotionPicture( motionPicture, new BindException( new BindException( new BindException(
                new BindException( new BindException( new BeanPropertyBindingResult( "Target", "Object Name" ) ) ) ) ) ) );
    }

    /**
     * Method under test: {@link MotionPictureController#createMotionPicture(MotionPicture, BindingResult)}
     */
    @Test
    void testCreateMotionPicture3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of MotionPictureController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        MotionPictureRepository motionPictureRepository = mock( MotionPictureRepository.class );
        when( motionPictureRepository.save( (MotionPicture) any() ) ).thenReturn( motionPicture );
        MotionPictureController motionPictureController = new MotionPictureController(
                new MotionPictureServiceImpl( motionPictureRepository ) );
        MotionPicture motionPicture1 = mock( MotionPicture.class );
        doNothing().when( motionPicture1 ).setCreated( (LocalDateTime) any() );
        doNothing().when( motionPicture1 ).setFictionalCharacters( (Collection<FictionalCharacter>) any() );
        doNothing().when( motionPicture1 ).setGenres( (Collection<Genre>) any() );
        doNothing().when( motionPicture1 ).setImage( (String) any() );
        doNothing().when( motionPicture1 ).setRating( (Integer) any() );
        doNothing().when( motionPicture1 ).setTitle( (String) any() );
        motionPicture1.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture1.setFictionalCharacters( new ArrayList<>() );
        motionPicture1.setGenres( new ArrayList<>() );
        motionPicture1.setImage( "Image" );
        motionPicture1.setRating( 1 );
        motionPicture1.setTitle( "Dr" );

        BeanPropertyBindingResult beanPropertyBindingResult = new BeanPropertyBindingResult( "Target", "Object Name" );
        beanPropertyBindingResult.addError( new ObjectError( "Object Name", "Default Message" ) );
        assertThrows( BindingResultException.class,
                () -> motionPictureController.createMotionPicture( motionPicture1, new BindException(
                        new BindException( new BindException( new BindException( new BindException( beanPropertyBindingResult ) ) ) ) ) ) );
        verify( motionPicture1 ).setCreated( (LocalDateTime) any() );
        verify( motionPicture1 ).setFictionalCharacters( (Collection<FictionalCharacter>) any() );
        verify( motionPicture1 ).setGenres( (Collection<Genre>) any() );
        verify( motionPicture1 ).setImage( (String) any() );
        verify( motionPicture1 ).setRating( (Integer) any() );
        verify( motionPicture1 ).setTitle( (String) any() );
    }

    /**
     * Method under test: {@link MotionPictureController#createMotionPicture(MotionPicture, BindingResult)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateMotionPicture4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of MotionPictureController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.validation.BindingResult.hasErrors()" because "bindingResult" is null
        //       at ar.com.saile.controllers.MotionPictureController.createMotionPicture(MotionPictureController.java:49)
        //   In order to prevent createMotionPicture(MotionPicture, BindingResult)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createMotionPicture(MotionPicture, BindingResult).
        //   See https://diff.blue/R013 to resolve this issue.

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        MotionPictureRepository motionPictureRepository = mock( MotionPictureRepository.class );
        when( motionPictureRepository.save( (MotionPicture) any() ) ).thenReturn( motionPicture );
        MotionPictureController motionPictureController = new MotionPictureController(
                new MotionPictureServiceImpl( motionPictureRepository ) );
        MotionPicture motionPicture1 = mock( MotionPicture.class );
        doNothing().when( motionPicture1 ).setCreated( (LocalDateTime) any() );
        doNothing().when( motionPicture1 ).setFictionalCharacters( (Collection<FictionalCharacter>) any() );
        doNothing().when( motionPicture1 ).setGenres( (Collection<Genre>) any() );
        doNothing().when( motionPicture1 ).setImage( (String) any() );
        doNothing().when( motionPicture1 ).setRating( (Integer) any() );
        doNothing().when( motionPicture1 ).setTitle( (String) any() );
        motionPicture1.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture1.setFictionalCharacters( new ArrayList<>() );
        motionPicture1.setGenres( new ArrayList<>() );
        motionPicture1.setImage( "Image" );
        motionPicture1.setRating( 1 );
        motionPicture1.setTitle( "Dr" );
        motionPictureController.createMotionPicture( motionPicture1, null );
    }

    /**
     * Method under test: {@link MotionPictureController#deleteMotionPicture(Long)}
     */
    @Test
    void testDeleteMotionPicture() throws Exception {

        doNothing().when( motionPictureService ).delete( (Long) any() );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete( "/movies/{id}", 123L );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( motionPictureController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNoContent() );
    }

    /**
     * Method under test: {@link MotionPictureController#deleteMotionPicture(Long)}
     */
    @Test
    void testDeleteMotionPicture2() throws Exception {

        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( new ArrayList<>() );
        BindingResultException bindingResultException = new BindingResultException(
                new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) );
        doThrow( bindingResultException ).when( motionPictureService ).delete( (Long) any() );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete( "/movies/{id}", 123L );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( motionPictureController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().is( 400 ) );
    }

    /**
     * Method under test: {@link MotionPictureController#getMotionPicture(Long)}
     */
    @Test
    void testGetMotionPicture() throws Exception {

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        when( motionPictureService.findById( (Long) any() ) ).thenReturn( motionPicture );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get( "/movies/{id}", 123L );
        MockMvcBuilders.standaloneSetup( motionPictureController )
                .build()
                .perform( requestBuilder )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( "application/json" ) )
                .andExpect( MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"image\":\"Image\",\"title\":\"Dr\",\"created\":[1,1,1,1,1],\"rating\":1,\"genres\":[],\"fictionalCharacters"
                                        + "\":[]}" ) );
    }

    /**
     * Method under test: {@link MotionPictureController#getMotionPicture(Long)}
     */
    @Test
    void testGetMotionPicture2() throws Exception {

        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( new ArrayList<>() );
        BindingResultException bindingResultException = new BindingResultException(
                new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) );
        when( motionPictureService.findById( (Long) any() ) ).thenThrow( bindingResultException );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get( "/movies/{id}", 123L );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( motionPictureController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().is( 400 ) );
    }

    /**
     * Method under test: {@link MotionPictureController#getMotionPictures(int, String)}
     */
    @Test
    void testGetMotionPictures() throws Exception {

        when( motionPictureService.findAll( (Integer) any(), (String) any() ) ).thenReturn( new PageImpl<>( new ArrayList<>() ) );
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get( "/movies" ).param( "order", "foo" );
        MockHttpServletRequestBuilder requestBuilder = paramResult.param( "page", String.valueOf( 1 ) );
        MockMvcBuilders.standaloneSetup( motionPictureController )
                .build()
                .perform( requestBuilder )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( "application/json" ) )
                .andExpect( MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[],\"pageable\":\"INSTANCE\",\"totalPages\":1,\"totalElements\":0,\"last\":true,\"size\":0,\"number"
                                        + "\":0,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"first\":true,\"numberOfElements\":0,\"empty"
                                        + "\":true}" ) );
    }

    /**
     * Method under test: {@link MotionPictureController#getMotionPictures(int, String)}
     */
    @Test
    void testGetMotionPictures2() throws Exception {

        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( new ArrayList<>() );
        BindingResultException bindingResultException = new BindingResultException(
                new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) );
        when( motionPictureService.findAll( (Integer) any(), (String) any() ) ).thenThrow( bindingResultException );
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get( "/movies" ).param( "order", "foo" );
        MockHttpServletRequestBuilder requestBuilder = paramResult.param( "page", String.valueOf( 1 ) );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( motionPictureController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().is( 400 ) );
    }

    /**
     * Method under test: {@link MotionPictureController#searchMotionPictures(int, String, String, String)}
     */
    @Test
    void testSearchMotionPictures() throws Exception {

        when( motionPictureService.findAll( (Integer) any(), (String) any() ) ).thenReturn( new PageImpl<>( new ArrayList<>() ) );
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get( "/movies" ).param( "order", "foo" );
        MockHttpServletRequestBuilder requestBuilder = paramResult.param( "page", String.valueOf( 1 ) );
        MockMvcBuilders.standaloneSetup( motionPictureController )
                .build()
                .perform( requestBuilder )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( "application/json" ) )
                .andExpect( MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[],\"pageable\":\"INSTANCE\",\"totalPages\":1,\"totalElements\":0,\"last\":true,\"size\":0,\"number"
                                        + "\":0,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"first\":true,\"numberOfElements\":0,\"empty"
                                        + "\":true}" ) );
    }

    /**
     * Method under test: {@link MotionPictureController#searchMotionPictures(int, String, String, String)}
     */
    @Test
    void testSearchMotionPictures2() throws Exception {

        when( motionPictureService.findAll( (Integer) any(), (Map<String, String>) any() ) ).thenReturn( new ArrayList<>() );
        when( motionPictureService.findAll( (Integer) any(), (String) any() ) ).thenReturn( new PageImpl<>( new ArrayList<>() ) );
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get( "/movies" ).param( "order", "foo" );
        MockHttpServletRequestBuilder requestBuilder = paramResult.param( "page", String.valueOf( 1 ) ).param( "genre", "foo" );
        MockMvcBuilders.standaloneSetup( motionPictureController )
                .build()
                .perform( requestBuilder )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( "application/json" ) )
                .andExpect( MockMvcResultMatchers.content().string( "[]" ) );
    }

    /**
     * Method under test: {@link MotionPictureController#searchMotionPictures(int, String, String, String)}
     */
    @Test
    void testSearchMotionPictures3() throws Exception {

        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( new ArrayList<>() );
        BindingResultException bindingResultException = new BindingResultException(
                new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) );
        when( motionPictureService.findAll( (Integer) any(), (Map<String, String>) any() ) ).thenThrow( bindingResultException );
        when( motionPictureService.findAll( (Integer) any(), (String) any() ) ).thenReturn( new PageImpl<>( new ArrayList<>() ) );
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get( "/movies" ).param( "order", "foo" );
        MockHttpServletRequestBuilder requestBuilder = paramResult.param( "page", String.valueOf( 1 ) ).param( "genre", "foo" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( motionPictureController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().is( 400 ) );
    }

    /**
     * Method under test: {@link MotionPictureController#updateMotionPicture(Long, MotionPicture, BindingResult)}
     */
    @Test
    void testUpdateMotionPicture() throws Exception {

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( LocalDateTime.of( 1, 1, 1, 1, 1 ) );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        when( motionPictureService.updateById( (Long) any(), (MotionPicture) any() ) ).thenReturn( motionPicture );

        MotionPicture motionPicture1 = new MotionPicture();
        motionPicture1.setCreated( null );
        motionPicture1.setFictionalCharacters( new ArrayList<>() );
        motionPicture1.setGenres( new ArrayList<>() );
        motionPicture1.setImage( "Image" );
        motionPicture1.setRating( 1 );
        motionPicture1.setTitle( "Dr" );
        String content = (new ObjectMapper()).writeValueAsString( motionPicture1 );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put( "/movies/{id}", 123L )
                .contentType( MediaType.APPLICATION_JSON )
                .content( content );
        MockMvcBuilders.standaloneSetup( motionPictureController )
                .build()
                .perform( requestBuilder )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( "application/json" ) )
                .andExpect( MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"image\":\"Image\",\"title\":\"Dr\",\"created\":[1,1,1,1,1],\"rating\":1,\"genres\":[],\"fictionalCharacters"
                                        + "\":[]}" ) );
    }

    /**
     * Method under test: {@link MotionPictureController#updateMotionPicture(Long, MotionPicture, BindingResult)}
     */
    @Test
    void testUpdateMotionPicture2() throws Exception {

        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( new ArrayList<>() );
        BindingResultException bindingResultException = new BindingResultException(
                new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) );
        when( motionPictureService.updateById( (Long) any(), (MotionPicture) any() ) ).thenThrow( bindingResultException );

        MotionPicture motionPicture = new MotionPicture();
        motionPicture.setCreated( null );
        motionPicture.setFictionalCharacters( new ArrayList<>() );
        motionPicture.setGenres( new ArrayList<>() );
        motionPicture.setImage( "Image" );
        motionPicture.setRating( 1 );
        motionPicture.setTitle( "Dr" );
        String content = (new ObjectMapper()).writeValueAsString( motionPicture );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put( "/movies/{id}", 123L )
                .contentType( MediaType.APPLICATION_JSON )
                .content( content );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( motionPictureController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().is( 400 ) );
    }
}

