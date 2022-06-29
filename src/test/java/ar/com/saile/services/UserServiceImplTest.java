package ar.com.saile.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.saile.domain.AppRole;
import ar.com.saile.domain.AppUser;
import ar.com.saile.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {

        AppUser appUser = new AppUser();
        appUser.setId( 123L );
        appUser.setIsAccountNonExpired( true );
        appUser.setIsAccountNonLocked( true );
        appUser.setIsCredentialsNonExpired( true );
        appUser.setIsEnabled( true );
        appUser.setPassword( "iloveyou" );
        appUser.setRoleCollection( new ArrayList<>() );
        appUser.setUsername( "janedoe" );
        Optional<AppUser> ofResult = Optional.of( appUser );
        when( userRepository.findByUsername( (String) any() ) ).thenReturn( ofResult );
        UserDetails actualLoadUserByUsernameResult = userServiceImpl.loadUserByUsername( "janedoe" );
        assertTrue( actualLoadUserByUsernameResult.getAuthorities().isEmpty() );
        assertTrue( actualLoadUserByUsernameResult.isEnabled() );
        assertTrue( actualLoadUserByUsernameResult.isCredentialsNonExpired() );
        assertTrue( actualLoadUserByUsernameResult.isAccountNonLocked() );
        assertTrue( actualLoadUserByUsernameResult.isAccountNonExpired() );
        assertEquals( "janedoe", actualLoadUserByUsernameResult.getUsername() );
        assertEquals( "iloveyou", actualLoadUserByUsernameResult.getPassword() );
        verify( userRepository ).findByUsername( (String) any() );
    }

    /**
     * Method under test: {@link UserServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {

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
        Optional<AppUser> ofResult = Optional.of( appUser );
        when( userRepository.findByUsername( (String) any() ) ).thenReturn( ofResult );
        assertThrows( UsernameNotFoundException.class, () -> userServiceImpl.loadUserByUsername( "janedoe" ) );
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
     * Method under test: {@link UserServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {

        when( userRepository.findByUsername( (String) any() ) ).thenReturn( Optional.empty() );
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
        assertThrows( UsernameNotFoundException.class, () -> userServiceImpl.loadUserByUsername( "janedoe" ) );
        verify( userRepository ).findByUsername( (String) any() );
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
     * Method under test: {@link UserServiceImpl#save(AppUser)}
     */
    @Test
    void testSave() {

        AppUser appUser = new AppUser();
        appUser.setId( 123L );
        appUser.setIsAccountNonExpired( true );
        appUser.setIsAccountNonLocked( true );
        appUser.setIsCredentialsNonExpired( true );
        appUser.setIsEnabled( true );
        appUser.setPassword( "iloveyou" );
        appUser.setRoleCollection( new ArrayList<>() );
        appUser.setUsername( "janedoe" );
        when( userRepository.save( (AppUser) any() ) ).thenReturn( appUser );

        AppUser appUser1 = new AppUser();
        appUser1.setId( 123L );
        appUser1.setIsAccountNonExpired( true );
        appUser1.setIsAccountNonLocked( true );
        appUser1.setIsCredentialsNonExpired( true );
        appUser1.setIsEnabled( true );
        appUser1.setPassword( "iloveyou" );
        appUser1.setRoleCollection( new ArrayList<>() );
        appUser1.setUsername( "janedoe" );
        assertSame( appUser, userServiceImpl.save( appUser1 ) );
        verify( userRepository ).save( (AppUser) any() );
    }
}

