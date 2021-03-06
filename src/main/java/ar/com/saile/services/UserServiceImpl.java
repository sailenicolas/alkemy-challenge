package ar.com.saile.services;

import ar.com.saile.domain.AppUser;
import ar.com.saile.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = findByUsername( username );
        return User.builder().username(user.getUsername()).password(user.getPassword()).authorities(user.getAuthorities()).build();

    }

    public AppUser findByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername( username ).orElseThrow( () -> new UsernameNotFoundException( "Username not found" ) );
    }
    public boolean existsByUsername(AppUser appUser) throws UsernameNotFoundException {
        return userRepository.findByUsername( appUser.getUsername() ).isPresent();
    }

    @Override
    public AppUser save(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userRepository.save(appUser);
    }
}
