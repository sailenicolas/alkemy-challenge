package ar.com.saile.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.com.saile.domain.Genre;
import ar.com.saile.exceptions.BindingResultException;
import ar.com.saile.services.GenreService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

@ContextConfiguration(classes = {GenreController.class})
@ExtendWith(SpringExtension.class)
class GenreControllerTest {

    @Autowired
    private GenreController genreController;

    @MockBean
    private GenreService genreService;

    /**
     * Method under test: {@link GenreController#createGenre(Genre, BindingResult)}
     */
    @Test
    void testCreateGenre() throws Exception {

        Genre genre = new Genre();
        genre.setImage( "Image" );
        genre.setMovies( new ArrayList<>() );
        genre.setName( "Name" );
        when( genreService.createMovie( (Genre) any() ) ).thenReturn( genre );

        Genre genre1 = new Genre();
        genre1.setImage( "Image" );
        genre1.setMovies( new ArrayList<>() );
        genre1.setName( "Name" );
        String content = (new ObjectMapper()).writeValueAsString( genre1 );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post( "/genres/" )
                .contentType( MediaType.APPLICATION_JSON )
                .content( content );
        MockMvcBuilders.standaloneSetup( genreController )
                .build()
                .perform( requestBuilder )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( "application/json" ) )
                .andExpect( MockMvcResultMatchers.content()
                        .string( "{\"id\":null,\"name\":\"Name\",\"image\":\"Image\",\"motionPictures\":[]}" ) );
    }

    /**
     * Method under test: {@link GenreController#createGenre(Genre, BindingResult)}
     */
    @Test
    void testCreateGenre2() throws Exception {

        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( new ArrayList<>() );
        BindingResultException bindingResultException = new BindingResultException(
                new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) );
        when( genreService.createMovie( (Genre) any() ) ).thenThrow( bindingResultException );

        Genre genre = new Genre();
        genre.setImage( "Image" );
        genre.setMovies( new ArrayList<>() );
        genre.setName( "Name" );
        String content = (new ObjectMapper()).writeValueAsString( genre );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post( "/genres/" )
                .contentType( MediaType.APPLICATION_JSON )
                .content( content );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( genreController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().is( 400 ) );
    }

    /**
     * Method under test: {@link GenreController#deleteGenre(Long)}
     */
    @Test
    void testDeleteGenre() throws Exception {

        doNothing().when( genreService ).deleteById( (Long) any() );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete( "/genres/{id}", 123L );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( genreController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNoContent() );
    }

    /**
     * Method under test: {@link GenreController#deleteGenre(Long)}
     */
    @Test
    void testDeleteGenre2() throws Exception {

        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( new ArrayList<>() );
        BindingResultException bindingResultException = new BindingResultException(
                new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) );
        doThrow( bindingResultException ).when( genreService ).deleteById( (Long) any() );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete( "/genres/{id}", 123L );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( genreController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().is( 400 ) );
    }

    /**
     * Method under test: {@link GenreController#getGenre(Long)}
     */
    @Test
    void testGetGenre() throws Exception {

        Genre genre = new Genre();
        genre.setImage( "Image" );
        genre.setMovies( new ArrayList<>() );
        genre.setName( "Name" );
        when( genreService.findById( (Long) any() ) ).thenReturn( genre );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get( "/genres/{id}", 123L );
        MockMvcBuilders.standaloneSetup( genreController )
                .build()
                .perform( requestBuilder )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( "application/json" ) )
                .andExpect( MockMvcResultMatchers.content()
                        .string( "{\"id\":null,\"name\":\"Name\",\"image\":\"Image\",\"motionPictures\":[]}" ) );
    }

    /**
     * Method under test: {@link GenreController#getGenre(Long)}
     */
    @Test
    void testGetGenre2() throws Exception {

        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( new ArrayList<>() );
        BindingResultException bindingResultException = new BindingResultException(
                new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) );
        when( genreService.findById( (Long) any() ) ).thenThrow( bindingResultException );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get( "/genres/{id}", 123L );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( genreController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().is( 400 ) );
    }

    /**
     * Method under test: {@link GenreController#getGenres(int, String)}
     */
    @Test
    void testGetGenres() throws Exception {

        when( genreService.findAll( anyInt(), (String) any() ) ).thenReturn( new PageImpl<>( new ArrayList<>() ) );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get( "/genres/" );
        MockMvcBuilders.standaloneSetup( genreController )
                .build()
                .perform( requestBuilder )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( "application/json" ) )
                .andExpect( MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[],\"pageable\":\"INSTANCE\",\"last\":true,\"totalElements\":0,\"totalPages\":1,\"size\":0,\"number"
                                        + "\":0,\"sort\":{\"empty\":true,\"unsorted\":true,\"sorted\":false},\"first\":true,\"numberOfElements\":0,\"empty"
                                        + "\":true}" ) );
    }

    /**
     * Method under test: {@link GenreController#getGenres(int, String)}
     */
    @Test
    void testGetGenres2() throws Exception {

        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( new ArrayList<>() );
        BindingResultException bindingResultException = new BindingResultException(
                new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) );
        when( genreService.findAll( anyInt(), (String) any() ) ).thenThrow( bindingResultException );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get( "/genres/" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( genreController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().is( 400 ) );
    }

    /**
     * Method under test: {@link GenreController#updateGenre(Long, Genre, BindingResult)}
     */
    @Test
    void testUpdateGenre() throws Exception {

        Genre genre = new Genre();
        genre.setImage( "Image" );
        genre.setMovies( new ArrayList<>() );
        genre.setName( "Name" );
        when( genreService.updateMovie( (Long) any(), (Genre) any() ) ).thenReturn( genre );

        Genre genre1 = new Genre();
        genre1.setImage( "Image" );
        genre1.setMovies( new ArrayList<>() );
        genre1.setName( "Name" );
        String content = (new ObjectMapper()).writeValueAsString( genre1 );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put( "/genres/{id}", 123L )
                .contentType( MediaType.APPLICATION_JSON )
                .content( content );
        MockMvcBuilders.standaloneSetup( genreController )
                .build()
                .perform( requestBuilder )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( "application/json" ) )
                .andExpect( MockMvcResultMatchers.content()
                        .string( "{\"id\":null,\"name\":\"Name\",\"image\":\"Image\",\"motionPictures\":[]}" ) );
    }

    /**
     * Method under test: {@link GenreController#updateGenre(Long, Genre, BindingResult)}
     */
    @Test
    void testUpdateGenre2() throws Exception {

        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( new ArrayList<>() );
        BindingResultException bindingResultException = new BindingResultException(
                new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) );
        when( genreService.updateMovie( (Long) any(), (Genre) any() ) ).thenThrow( bindingResultException );

        Genre genre = new Genre();
        genre.setImage( "Image" );
        genre.setMovies( new ArrayList<>() );
        genre.setName( "Name" );
        String content = (new ObjectMapper()).writeValueAsString( genre );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put( "/genres/{id}", 123L )
                .contentType( MediaType.APPLICATION_JSON )
                .content( content );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( genreController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().is( 400 ) );
    }
}

