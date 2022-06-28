package ar.com.saile.component;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnumPathVariableConverter implements Converter<String, FictionalPathTypeVariable> {

    @Override
    public FictionalPathTypeVariable convert(String source) {
        return FictionalPathTypeVariable.valueOf(source.toUpperCase());
    }
}
