package com.teste.cadastro.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnderecoTest {

    @Test
    void testGettersAndSetters() {
        Endereco endereco = new Endereco();
        endereco.setCep("12345678");
        endereco.setNomeRua("Rua Exemplo");

        assertEquals("12345678", endereco.getCep());
        assertEquals("Rua Exemplo", endereco.getNomeRua());
    }
}
