package ar.com.saile.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class SecurityConfig {
    @Value("${jwt.key.public}")
    RSAPublicKey rsaPublicKey;

    @Value("${jwt.key.private}")
    RSAPrivateKey rsaPrivateKey;

    @Bean
    JwtDecoder jwtDecoder() {

        return NimbusJwtDecoder.withPublicKey(rsaPublicKey).signatureAlgorithm(SignatureAlgorithm.RS512).build();
    }
    @Bean
    JwtEncoder jwtEncoder() {

        JWK jwk = new RSAKey.Builder(rsaPublicKey).privateKey(rsaPrivateKey).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
    @Bean
    Algorithm algorithm(){
        return Algorithm.RSA512(rsaPublicKey, rsaPrivateKey);
    }

    @Bean
    JWTCreator.Builder getBuilder(){
        return JWT.create();
    }
}
