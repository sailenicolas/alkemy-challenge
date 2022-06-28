package ar.com.saile.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
public class ExceptionResponse {

    public ExceptionResponse(String message, Map<String, Object> details) {
        this.message = message;
        this.details = details;
    }
    private String message;

    private Map<String, Object> details;

}
