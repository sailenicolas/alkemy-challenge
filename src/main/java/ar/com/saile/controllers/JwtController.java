package ar.com.saile.controllers;

import ar.com.saile.services.SecurityService;
import ar.com.saile.services.UserService;
import ar.com.saile.domain.LoginResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@AllArgsConstructor
public class JwtController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final SecurityService securityService;
    @PostMapping(path = "/login")
    public LoginResult login(
            @RequestParam String username,
            @RequestParam String password, HttpServletRequest request) {
        UserDetails userDetails;
        try {
            userDetails = userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new LoginResult(securityService.createToken(userDetails, request.getServerName()));
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
    }

}