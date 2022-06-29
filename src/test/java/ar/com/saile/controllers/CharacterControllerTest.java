package ar.com.saile.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.com.saile.component.FictionalPathTypeVariable;
import ar.com.saile.domain.FictionalCharacter;
import ar.com.saile.exceptions.BindingResultException;
import ar.com.saile.services.FictionalCharacterService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

@ContextConfiguration(classes = {CharacterController.class})
@ExtendWith(SpringExtension.class)
class CharacterControllerTest {

    @Autowired
    private CharacterController characterController;

    @MockBean
    private FictionalCharacterService fictionalCharacterService;

    /**
     * Method under test: {@link CharacterController#createFictional(FictionalCharacter, BindingResult)}
     */
    @Test
    void testCreateFictional() throws Exception {

        FictionalCharacter fictionalCharacter = new FictionalCharacter();
        fictionalCharacter.setAge( 1 );
        fictionalCharacter.setHistory( "History" );
        fictionalCharacter.setId( 123L );
        fictionalCharacter.setName( "Name" );
        fictionalCharacter.setSeries( new ArrayList<>() );
        fictionalCharacter.setWeigh( 1 );
        String content = (new ObjectMapper()).writeValueAsString( fictionalCharacter );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post( "/characters/" )
                .contentType( MediaType.APPLICATION_JSON )
                .content( content );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().is( 405 ) );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType2() throws Exception {

        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType3() throws Exception {

        SecurityMockMvcRequestBuilders.LogoutRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.logout();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType4() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.secure( true );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType5() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.characterEncoding( "UTF-8" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType6() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.characterEncoding( "Encoding" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType7() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.characterEncoding( "42" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType8() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.characterEncoding( "Content-Length" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType9() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.characterEncoding( "Transfer-Encoding" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType10() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.secure( true );
        postResult.characterEncoding( "UTF-8" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType11() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.secure( true );
        postResult.characterEncoding( "Encoding" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType12() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.secure( true );
        postResult.characterEncoding( "42" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType13() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.secure( true );
        postResult.characterEncoding( "Content-Length" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType14() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.content( "AAAAAAAA".getBytes( "UTF-8" ) );
        postResult.characterEncoding( "UTF-8" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType15() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.content( "AAAAAAAA".getBytes( "UTF-8" ) );
        postResult.characterEncoding( "Encoding" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType16() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.content( "AAAAAAAA".getBytes( "UTF-8" ) );
        postResult.characterEncoding( "42" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType17() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.content( "AAAAAAAA".getBytes( "UTF-8" ) );
        postResult.characterEncoding( "Content-Length" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType18() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.content( "https://example.org/example" );
        postResult.characterEncoding( "UTF-8" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType19() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.content( "https://example.org/example" );
        postResult.characterEncoding( "Encoding" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType20() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.content( "https://example.org/example" );
        postResult.characterEncoding( "42" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType21() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.content( "https://example.org/example" );
        postResult.characterEncoding( "Content-Length" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType22() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.contentType( "text/plain" );
        postResult.characterEncoding( "UTF-8" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType23() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.contentType( "text/plain" );
        postResult.characterEncoding( "Encoding" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType24() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.contentType( "text/plain" );
        postResult.characterEncoding( "42" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType25() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.contentType( "text/plain" );
        postResult.characterEncoding( "Content-Length" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType26() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.accept( "https://example.org/example" );
        postResult.characterEncoding( "UTF-8" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType27() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.accept( "https://example.org/example" );
        postResult.characterEncoding( "Encoding" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType28() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.accept( "https://example.org/example" );
        postResult.characterEncoding( "42" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#createFictionalByType(FictionalPathTypeVariable, Long, Long, HttpServletResponse)}
     */
    @Test
    void testCreateFictionalByType29() throws Exception {

        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        postResult.accept( "https://example.org/example" );
        postResult.characterEncoding( "Content-Length" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( postResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional2() throws Exception {

        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional3() throws Exception {

        SecurityMockMvcRequestBuilders.LogoutRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.logout();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional4() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.secure( true );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional5() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.characterEncoding( "UTF-8" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional6() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.characterEncoding( "Encoding" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional7() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.characterEncoding( "42" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional8() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.characterEncoding( "Content-Length" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional9() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.characterEncoding( "Transfer-Encoding" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional10() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.secure( true );
        deleteResult.characterEncoding( "UTF-8" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional11() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.secure( true );
        deleteResult.characterEncoding( "Encoding" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional12() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.secure( true );
        deleteResult.characterEncoding( "42" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional13() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.secure( true );
        deleteResult.characterEncoding( "Content-Length" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional14() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.content( "AAAAAAAA".getBytes( "UTF-8" ) );
        deleteResult.characterEncoding( "UTF-8" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional15() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.content( "AAAAAAAA".getBytes( "UTF-8" ) );
        deleteResult.characterEncoding( "Encoding" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional16() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.content( "AAAAAAAA".getBytes( "UTF-8" ) );
        deleteResult.characterEncoding( "42" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional17() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.content( "AAAAAAAA".getBytes( "UTF-8" ) );
        deleteResult.characterEncoding( "Content-Length" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional18() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.content( "https://example.org/example" );
        deleteResult.characterEncoding( "UTF-8" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional19() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.content( "https://example.org/example" );
        deleteResult.characterEncoding( "Encoding" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional20() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.content( "https://example.org/example" );
        deleteResult.characterEncoding( "42" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional21() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.content( "https://example.org/example" );
        deleteResult.characterEncoding( "Content-Length" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional22() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.contentType( "text/plain" );
        deleteResult.characterEncoding( "UTF-8" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional23() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.contentType( "text/plain" );
        deleteResult.characterEncoding( "Encoding" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional24() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.contentType( "text/plain" );
        deleteResult.characterEncoding( "42" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional25() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.contentType( "text/plain" );
        deleteResult.characterEncoding( "Content-Length" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional26() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.accept( "https://example.org/example" );
        deleteResult.characterEncoding( "UTF-8" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional27() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.accept( "https://example.org/example" );
        deleteResult.characterEncoding( "Encoding" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional28() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.accept( "https://example.org/example" );
        deleteResult.characterEncoding( "42" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(FictionalPathTypeVariable, Long, Long)}
     */
    @Test
    void testDeleteFictional29() throws Exception {

        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete(
                "/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}", FictionalPathTypeVariable.UNKOWN, 1L,
                1L );
        deleteResult.accept( "https://example.org/example" );
        deleteResult.characterEncoding( "Content-Length" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( deleteResult );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#deleteFictional(Long)}
     */
    @Test
    void testDeleteFictional30() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete( "/characters/{id}", 123L );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#getCharacters(int, String)}
     */
    @Test
    void testGetCharacters() throws Exception {

        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get( "/" ).param( "order", "foo" );
        MockHttpServletRequestBuilder requestBuilder = paramResult.param( "page", String.valueOf( 1 ) );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#getFictional(Long)}
     */
    @Test
    void testGetFictional() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get( "/characters/{id}", 123L );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#getFictional(Long)}
     */
    @Test
    void testGetFictional2() throws Exception {

        when( fictionalCharacterService.findAll( anyInt(), (String) any() ) ).thenReturn( new PageImpl<>( new ArrayList<>() ) );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get( "/characters/{id}", "", "Uri Vars" );
        MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( requestBuilder )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( "application/json" ) )
                .andExpect( MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[],\"pageable\":\"INSTANCE\",\"last\":true,\"totalPages\":1,\"totalElements\":0,\"size\":0,\"number"
                                        + "\":0,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"first\":true,\"numberOfElements\":0,\"empty"
                                        + "\":true}" ) );
    }

    /**
     * Method under test: {@link CharacterController#getFictional(Long)}
     */
    @Test
    void testGetFictional3() throws Exception {

        BindingResult bindingResult = mock( BindingResult.class );
        when( bindingResult.getFieldErrors() ).thenReturn( new ArrayList<>() );
        BindingResultException bindingResultException = new BindingResultException(
                new BindException( new BindException( new BindException( new BindException( bindingResult ) ) ) ) );
        when( fictionalCharacterService.findAll( anyInt(), (String) any() ) ).thenThrow( bindingResultException );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get( "/characters/{id}", "", "Uri Vars" );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().is( 400 ) );
    }

    /**
     * Method under test: {@link CharacterController#searchCharacters(int, String, String, String, String)}
     */
    @Test
    void testSearchCharacters() throws Exception {

        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get( "/" ).param( "order", "foo" );
        MockHttpServletRequestBuilder requestBuilder = paramResult.param( "page", String.valueOf( 1 ) );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }

    /**
     * Method under test: {@link CharacterController#updateFictional(Long, FictionalCharacter, BindingResult)}
     */
    @Test
    void testUpdateFictional() throws Exception {

        FictionalCharacter fictionalCharacter = new FictionalCharacter();
        fictionalCharacter.setAge( 1 );
        fictionalCharacter.setHistory( "History" );
        fictionalCharacter.setId( 123L );
        fictionalCharacter.setName( "Name" );
        fictionalCharacter.setSeries( new ArrayList<>() );
        fictionalCharacter.setWeigh( 1 );
        String content = (new ObjectMapper()).writeValueAsString( fictionalCharacter );
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put( "/characters/{id}", 123L )
                .contentType( MediaType.APPLICATION_JSON )
                .content( content );
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup( characterController )
                .build()
                .perform( requestBuilder );
        actualPerformResult.andExpect( MockMvcResultMatchers.status().isNotFound() );
    }
}

