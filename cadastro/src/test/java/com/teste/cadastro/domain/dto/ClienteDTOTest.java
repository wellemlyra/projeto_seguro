package com.teste.cadastro.domain.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteDTOTest {

    @Test
    void testGettersAndSetters() {
        EnderecoDTO enderecoDTO = new EnderecoDTO("12345678", "Rua Exemplo");
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("12345678901");
        clienteDTO.setNome("João Silva");
        clienteDTO.setDataNascimento(LocalDate.of(1990, 10, 10));
        clienteDTO.setTelefone("1234567890");
        clienteDTO.setEndereco(enderecoDTO);

        assertEquals("12345678901", clienteDTO.getCpf());
        assertEquals("João Silva", clienteDTO.getNome());
        assertEquals(LocalDate.of(1990, 10, 10), clienteDTO.getDataNascimento());
        assertEquals("1234567890", clienteDTO.getTelefone());
        assertEquals(enderecoDTO, clienteDTO.getEndereco());
    }
}
