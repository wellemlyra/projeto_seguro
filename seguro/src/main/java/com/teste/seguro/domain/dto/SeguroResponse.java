package com.teste.seguro.domain.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SeguroResponse {
    private HttpStatus status;
    private String message;
    private Object data;

    public SeguroResponse(HttpStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public SeguroResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
