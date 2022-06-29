package ar.com.saile.controllers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.saile.domain.AppRole;
import ar.com.saile.domain.AppUser;
import ar.com.saile.repositories.UserRepository;
import ar.com.saile.services.SecurityService;
import ar.com.saile.services.UserService;
import ar.com.saile.services.UserServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

class JwtControllerTest {

    /**
     * Method under test: {@link JwtController#login(String, String, HttpServletRequest)}
     */
    @Test
    void testLogin() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R010 Timeout.
        //   Creating the arrange/act section of your test took more than
        //   20,000 seconds. This often happens because Diffblue Cover ran code in your
        //   project which requests user input (System.in), blocks on a lock, or runs into
        //   an infinite or very long loop.
        //   See https://diff.blue/R010 to resolve this issue.

        AppUser appUser = new AppUser();
        appUser.setId( 123L );
        appUser.setIsAccountNonExpired( true );
        appUser.setIsAccountNonLocked( true );
        appUser.setIsCredentialsNonExpired( true );
        appUser.setIsEnabled( true );
        appUser.setPassword( "iloveyou" );
        appUser.setRoleCollection( new ArrayList<>() );
        appUser.setUsername( "janedoe" );
        UserRepository userRepository = mock( UserRepository.class );
        when( userRepository.findByUsername( (String) any() ) ).thenReturn( Optional.of( appUser ) );
        UserServiceImpl userService = new UserServiceImpl( userRepository, new BCryptPasswordEncoder() );

        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        JwtController jwtController = new JwtController( userService, passwordEncoder, new SecurityService( null, null ) );
        assertThrows( ResponseStatusException.class, () -> jwtController.login( "foo", "foo", new MockHttpServletRequest() ) );
        verify( userRepository ).findByUsername( (String) any() );
    }

    /**
     * Method under test: {@link JwtController#login(String, String, HttpServletRequest)}
     */
    @Test
    void testLogin2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R010 Timeout.
        //   Creating the arrange/act section of your test took more than
        //   20,000 seconds. This often happens because Diffblue Cover ran code in your
        //   project which requests user input (System.in), blocks on a lock, or runs into
        //   an infinite or very long loop.
        //   See https://diff.blue/R010 to resolve this issue.

        AppUser appUser = mock( AppUser.class );
        when( (Collection<GrantedAuthority>) appUser.getAuthorities() ).thenThrow( new UsernameNotFoundException( "Msg" ) );
        when( appUser.getPassword() ).thenReturn( "iloveyou" );
        when( appUser.getUsername() ).thenReturn( "janedoe" );
        doNothing().when( appUser ).setId( (Long) any() );
        doNothing().when( appUser ).setIsAccountNonExpired( (Boolean) any() );
        doNothing().when( appUser ).setIsAccountNonLocked( (Boolean) any() );
        doNothing().when( appUser ).setIsCredentialsNonExpired( (Boolean) any() );
        doNothing().when( appUser ).setIsEnabled( (Boolean) any() );
        doNothing().when( appUser ).setPassword( (String) any() );
        doNothing().when( appUser ).setRoleCollection( (Collection<AppRole>) any() );
        doNothing().when( appUser ).setUsername( (String) any() );
        appUser.setId( 123L );
        appUser.setIsAccountNonExpired( true );
        appUser.setIsAccountNonLocked( true );
        appUser.setIsCredentialsNonExpired( true );
        appUser.setIsEnabled( true );
        appUser.setPassword( "iloveyou" );
        appUser.setRoleCollection( new ArrayList<>() );
        appUser.setUsername( "janedoe" );
        UserRepository userRepository = mock( UserRepository.class );
        when( userRepository.findByUsername( (String) any() ) ).thenReturn( Optional.of( appUser ) );
        UserServiceImpl userService = new UserServiceImpl( userRepository, new BCryptPasswordEncoder() );

        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        JwtController jwtController = new JwtController( userService, passwordEncoder, new SecurityService( null, null ) );
        assertThrows( ResponseStatusException.class, () -> jwtController.login( "foo", "foo", new MockHttpServletRequest() ) );
        verify( userRepository ).findByUsername( (String) any() );
        verify( appUser ).getPassword();
        verify( appUser ).getUsername();
        verify( appUser ).getAuthorities();
        verify( appUser ).setId( (Long) any() );
        verify( appUser ).setIsAccountNonExpired( (Boolean) any() );
        verify( appUser ).setIsAccountNonLocked( (Boolean) any() );
        verify( appUser ).setIsCredentialsNonExpired( (Boolean) any() );
        verify( appUser ).setIsEnabled( (Boolean) any() );
        verify( appUser ).setPassword( (String) any() );
        verify( appUser ).setRoleCollection( (Collection<AppRole>) any() );
        verify( appUser ).setUsername( (String) any() );
    }

    /**
     * Method under test: {@link JwtController#login(String, String, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLogin3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R010 Timeout.
        //   Creating the arrange/act section of your test took more than
        //   20,000 seconds. This often happens because Diffblue Cover ran code in your
        //   project which requests user input (System.in), blocks on a lock, or runs into
        //   an infinite or very long loop.
        //   See https://diff.blue/R010 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.web.server.ResponseStatusException: 100 CONTINUE
        //       at ar.com.saile.services.UserServiceImpl.loadUserByUsername(UserServiceImpl.java:27)
        //       at ar.com.saile.controllers.JwtController.login(JwtController.java:31)
        //   In order to prevent login(String, String, HttpServletRequest)
        //   from throwing ResponseStatusException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   login(String, String, HttpServletRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        AppUser appUser = mock( AppUser.class );
        when( (Collection<GrantedAuthority>) appUser.getAuthorities() )
                .thenThrow( new ResponseStatusException( HttpStatus.CONTINUE ) );
        when( appUser.getPassword() ).thenReturn( "iloveyou" );
        when( appUser.getUsername() ).thenReturn( "janedoe" );
        doNothing().when( appUser ).setId( (Long) any() );
        doNothing().when( appUser ).setIsAccountNonExpired( (Boolean) any() );
        doNothing().when( appUser ).setIsAccountNonLocked( (Boolean) any() );
        doNothing().when( appUser ).setIsCredentialsNonExpired( (Boolean) any() );
        doNothing().when( appUser ).setIsEnabled( (Boolean) any() );
        doNothing().when( appUser ).setPassword( (String) any() );
        doNothing().when( appUser ).setRoleCollection( (Collection<AppRole>) any() );
        doNothing().when( appUser ).setUsername( (String) any() );
        appUser.setId( 123L );
        appUser.setIsAccountNonExpired( true );
        appUser.setIsAccountNonLocked( true );
        appUser.setIsCredentialsNonExpired( true );
        appUser.setIsEnabled( true );
        appUser.setPassword( "iloveyou" );
        appUser.setRoleCollection( new ArrayList<>() );
        appUser.setUsername( "janedoe" );
        UserRepository userRepository = mock( UserRepository.class );
        when( userRepository.findByUsername( (String) any() ) ).thenReturn( Optional.of( appUser ) );
        UserServiceImpl userService = new UserServiceImpl( userRepository, new BCryptPasswordEncoder() );

        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        JwtController jwtController = new JwtController( userService, passwordEncoder, new SecurityService( null, null ) );
        jwtController.login( "foo", "foo", new MockHttpServletRequest() );
    }

    /**
     * Method under test: {@link JwtController#login(String, String, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLogin4() throws UsernameNotFoundException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R010 Timeout.
        //   Creating the arrange/act section of your test took more than
        //   20,000 seconds. This often happens because Diffblue Cover ran code in your
        //   project which requests user input (System.in), blocks on a lock, or runs into
        //   an infinite or very long loop.
        //   See https://diff.blue/R010 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.userdetails.UserDetails.getPassword()" because "userDetails" is null
        //       at ar.com.saile.controllers.JwtController.login(JwtController.java:36)
        //   In order to prevent login(String, String, HttpServletRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   login(String, String, HttpServletRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        AppUser appUser = mock( AppUser.class );
        when( (Collection<GrantedAuthority>) appUser.getAuthorities() ).thenThrow( new UsernameNotFoundException( "Msg" ) );
        when( appUser.getPassword() ).thenReturn( "iloveyou" );
        when( appUser.getUsername() ).thenReturn( "janedoe" );
        doNothing().when( appUser ).setId( (Long) any() );
        doNothing().when( appUser ).setIsAccountNonExpired( (Boolean) any() );
        doNothing().when( appUser ).setIsAccountNonLocked( (Boolean) any() );
        doNothing().when( appUser ).setIsCredentialsNonExpired( (Boolean) any() );
        doNothing().when( appUser ).setIsEnabled( (Boolean) any() );
        doNothing().when( appUser ).setPassword( (String) any() );
        doNothing().when( appUser ).setRoleCollection( (Collection<AppRole>) any() );
        doNothing().when( appUser ).setUsername( (String) any() );
        appUser.setId( 123L );
        appUser.setIsAccountNonExpired( true );
        appUser.setIsAccountNonLocked( true );
        appUser.setIsCredentialsNonExpired( true );
        appUser.setIsEnabled( true );
        appUser.setPassword( "iloveyou" );
        appUser.setRoleCollection( new ArrayList<>() );
        appUser.setUsername( "janedoe" );
        Optional.of( appUser );
        UserService userService = mock( UserService.class );
        when( userService.loadUserByUsername( (String) any() ) ).thenReturn( null );
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        JwtController jwtController = new JwtController( userService, passwordEncoder, new SecurityService( null, null ) );
        jwtController.login( "foo", "foo", new MockHttpServletRequest() );
    }

    /**
     * Method under test: {@link JwtController#login(String, String, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLogin5() throws UsernameNotFoundException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R010 Timeout.
        //   Creating the arrange/act section of your test took more than
        //   20,000 seconds. This often happens because Diffblue Cover ran code in your
        //   project which requests user input (System.in), blocks on a lock, or runs into
        //   an infinite or very long loop.
        //   See https://diff.blue/R010 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.crypto.password.PasswordEncoder.matches(java.lang.CharSequence, String)" because "this.passwordEncoder" is null
        //       at ar.com.saile.controllers.JwtController.login(JwtController.java:36)
        //   In order to prevent login(String, String, HttpServletRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   login(String, String, HttpServletRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        AppUser appUser = mock( AppUser.class );
        when( (Collection<GrantedAuthority>) appUser.getAuthorities() ).thenThrow( new UsernameNotFoundException( "Msg" ) );
        when( appUser.getPassword() ).thenReturn( "iloveyou" );
        when( appUser.getUsername() ).thenReturn( "janedoe" );
        doNothing().when( appUser ).setId( (Long) any() );
        doNothing().when( appUser ).setIsAccountNonExpired( (Boolean) any() );
        doNothing().when( appUser ).setIsAccountNonLocked( (Boolean) any() );
        doNothing().when( appUser ).setIsCredentialsNonExpired( (Boolean) any() );
        doNothing().when( appUser ).setIsEnabled( (Boolean) any() );
        doNothing().when( appUser ).setPassword( (String) any() );
        doNothing().when( appUser ).setRoleCollection( (Collection<AppRole>) any() );
        doNothing().when( appUser ).setUsername( (String) any() );
        appUser.setId( 123L );
        appUser.setIsAccountNonExpired( true );
        appUser.setIsAccountNonLocked( true );
        appUser.setIsCredentialsNonExpired( true );
        appUser.setIsEnabled( true );
        appUser.setPassword( "iloveyou" );
        appUser.setRoleCollection( new ArrayList<>() );
        appUser.setUsername( "janedoe" );
        Optional.of( appUser );
        UserService userService = mock( UserService.class );
        when( userService.loadUserByUsername( (String) any() ) ).thenReturn( new AppUser( "janedoe" ) );
        JwtController jwtController = new JwtController( userService, null, new SecurityService( null, null ) );
        jwtController.login( "foo", "foo", new MockHttpServletRequest() );
    }
}

