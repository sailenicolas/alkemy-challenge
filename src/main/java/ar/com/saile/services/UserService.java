package ar.com.saile.services;

import ar.com.saile.domain.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    AppUser save(AppUser appUser);
}
