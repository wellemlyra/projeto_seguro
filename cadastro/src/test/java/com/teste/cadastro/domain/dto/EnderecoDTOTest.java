package com.teste.cadastro.domain.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnderecoDTOTest {

    @Test
    void testGettersAndSetters() {
        EnderecoDTO enderecoDTO = new EnderecoDTO("12345678", "Rua Exemplo");

        assertEquals("12345678", enderecoDTO.getCep());
        assertEquals("Rua Exemplo", enderecoDTO.getNomeRua());
    }
}
