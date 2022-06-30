package ar.com.saile.controllers;

import ar.com.saile.domain.AppUser;
import ar.com.saile.domain.LoginResult;
import ar.com.saile.domain.RegisterResult;
import ar.com.saile.exceptions.BindingResultException;
import ar.com.saile.services.SecurityService;
import ar.com.saile.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {

    private final SecurityService securityService;
    @PostMapping(path = "/auth/login")
    public LoginResult login(@Valid  @JsonView(Views.LoginUserForm.class) @RequestBody AppUser appUser, BindingResult bindingResult, HttpServletRequest request) {
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
        return securityService.login(appUser, request);
    }
    @JsonView(Views.RegisterResult.class)
    @PostMapping(path = "/auth/register")
    public RegisterResult register(
            @Valid @RequestBody @JsonView(Views.RegisterUserForm.class) AppUser appUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
        return securityService.register(appUser);

    }

}