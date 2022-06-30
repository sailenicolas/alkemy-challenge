package ar.com.saile.services;

import ar.com.saile.domain.AppUser;
import ar.com.saile.domain.LoginResult;
import ar.com.saile.domain.RegisterResult;
import ar.com.saile.exceptions.LoginFailedException;
import ar.com.saile.exceptions.RegisterFailedException;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UserService userService;
    private final Algorithm algorithm;
    private final BCryptPasswordEncoder passwordEncoder;

    private final JWTCreator.Builder builder;

    public String createToken(UserDetails userDetails, String issuer) {

        LocalDateTime now = LocalDateTime.now();
        Date expiresAt = new Date();
        Date expiredAt = new Date( now.plusHours( 6 ).toInstant(ZoneOffset.UTC ).toEpochMilli() );

        return builder
                .withNotBefore( expiresAt )
                .withSubject(userDetails.getUsername())
                .withExpiresAt( expiredAt )
                .withIssuer(issuer)
                .withClaim("username", userDetails.getUsername())
                .sign(algorithm);
    }

    public LoginResult login(AppUser appUser, HttpServletRequest request) throws LoginFailedException {
        AppUser userDetails;
        try {
            userDetails = userService.findByUsername(appUser.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new LoginFailedException("Login FAILED");
        }

        if (passwordEncoder.matches(appUser.getPassword(), userDetails.getPassword())) {
            if(appUser.getIsEnabled())
                return new LoginResult(this.createToken(userDetails, request.getServerName()));
            throw new LoginFailedException.UserNotActivatedException("USER NOT ACTIVATED");
        }
        throw new LoginFailedException("Login FAILED");
    }

    public RegisterResult register(AppUser appUser) {
        boolean appUserFound = userService.existsByUsername( appUser );
        if(appUserFound)
            throw new RegisterFailedException.UsernameAlreadyTakenException( "ALREADY_TAKEN_USERNAME" );
        AppUser user = userService.save( appUser );
        return new RegisterResult("TO_BE_ACTIVATED", user.getIsEnabled(), user);
    }
}
