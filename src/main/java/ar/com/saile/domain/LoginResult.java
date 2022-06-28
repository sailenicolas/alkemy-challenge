package ar.com.saile.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResult {
    private String jwt;
    public LoginResult(String jwt) {
        this.jwt = jwt;
    }
}
