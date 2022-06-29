package ar.com.saile.services;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.saile.domain.AppUser;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureGenerationException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SecurityService.class, JWTCreator.Builder.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SecurityServiceTest {

    @MockBean
    private Algorithm algorithm;

    @Autowired
    private SecurityService securityService;

    /**
     * Method under test: {@link SecurityService#createToken(UserDetails, String)}
     */
    @Test
    void testCreateToken() throws SignatureGenerationException {

        when( algorithm.sign( (byte[]) any(), (byte[]) any() ) ).thenReturn( "AAAAAAAA".getBytes( StandardCharsets.UTF_8 ) );
        when( algorithm.getName() ).thenReturn( "Name" );
        when( algorithm.getSigningKeyId() ).thenReturn( "42" );
        securityService.createToken( new AppUser( "janedoe" ), "Issuer" );
        verify( algorithm ).sign( (byte[]) any(), (byte[]) any() );
        verify( algorithm ).getName();
        verify( algorithm ).getSigningKeyId();
    }

    /**
     * Method under test: {@link SecurityService#createToken(UserDetails, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateToken2() throws SignatureGenerationException, UnsupportedEncodingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.userdetails.UserDetails.getUsername()" because "userDetails" is null
        //       at ar.com.saile.services.SecurityService.createToken(SecurityService.java:30)
        //   In order to prevent createToken(UserDetails, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createToken(UserDetails, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when( algorithm.sign( (byte[]) any(), (byte[]) any() ) ).thenReturn( "AAAAAAAA".getBytes( StandardCharsets.UTF_8 ) );
        when( algorithm.getName() ).thenReturn( "Name" );
        when( algorithm.getSigningKeyId() ).thenReturn( "42" );
        securityService.createToken( null, "Issuer" );
    }

    /**
     * Method under test: {@link SecurityService#createToken(UserDetails, String)}
     */
    @Test
    void testCreateToken3() throws SignatureGenerationException {

        when( algorithm.sign( (byte[]) any(), (byte[]) any() ) ).thenReturn( "AAAAAAAA".getBytes( StandardCharsets.UTF_8 ) );
        when( algorithm.getName() ).thenReturn( "Name" );
        when( algorithm.getSigningKeyId() ).thenReturn( "42" );
        securityService.createToken( new AppUser(), "Issuer" );
        verify( algorithm ).sign( (byte[]) any(), (byte[]) any() );
        verify( algorithm ).getName();
        verify( algorithm ).getSigningKeyId();
    }
}

