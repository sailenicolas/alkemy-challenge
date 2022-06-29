package ar.com.saile.security;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WebApiSecurityConfig.class})
@ExtendWith(SpringExtension.class)
class WebApiSecurityConfigTest {

    @MockBean
    private JwtDecoder jwtDecoder;

    @Autowired
    private WebApiSecurityConfig webApiSecurityConfig;

    /**
     * Method under test: {@link WebApiSecurityConfig#passwordEncoder()}
     */
    @Test
    void testPasswordEncoder() {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by passwordEncoder()
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        webApiSecurityConfig.passwordEncoder();
    }

    /**
     * Method under test: {@link WebApiSecurityConfig#configure(HttpSecurity)}
     */
    @Test
    void testConfigure() throws Exception {

        assertTrue( webApiSecurityConfig.configure( null ) instanceof DefaultSecurityFilterChain );
    }
}

