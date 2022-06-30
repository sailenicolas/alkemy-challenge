package ar.com.saile.component;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

@Deprecated
public enum FictionalPathTypeVariable {
    @JsonEnumDefaultValue
    UNKOWN("UNKOWN"),
    MOVIES("movies"),
    SERIES("series");

    FictionalPathTypeVariable(String series) {

    }

}
