package ar.com.saile.services;

import ar.com.saile.domain.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    AppUser save(AppUser appUser);

    AppUser findByUsername(String username);

    boolean existsByUsername(AppUser appUser) throws UsernameNotFoundException;
}
