package ar.com.saile.domain;

import ar.com.saile.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

@JsonView(Views.RegisterResult.class)
public record RegisterResult (String nextStep, Boolean activated, AppUser registeredUser) implements Serializable {
}
