package com.teste.cadastro.domain.dto;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteResponseTest {

    @Test
    void testGettersAndSetters() {
        ClienteResponse response = new ClienteResponse(HttpStatus.OK, "Sucesso", new Object());

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Sucesso", response.getMessage());
        assertEquals(Object.class, response.getData().getClass());
    }
}
