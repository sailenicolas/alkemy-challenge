package ar.com.saile.services;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SecurityService {
   private final Algorithm algorithm;

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
}
