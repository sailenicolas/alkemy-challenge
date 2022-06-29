package ar.com.saile.controllers;

import ar.com.saile.domain.LoginResult;
import ar.com.saile.services.SecurityService;
import ar.com.saile.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

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