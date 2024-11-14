package com.teste.cadastro.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testGettersAndSetters() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf("12345678901");
        cliente.setNome("João da Silva");
        cliente.setDataNascimento(LocalDate.of(1990, 1, 1));
        cliente.setTelefone("123456789");

        Endereco endereco = new Endereco();
        endereco.setCep("12345678");
        endereco.setNomeRua("Rua A");
        cliente.setEndereco(endereco);

        assertEquals(1L, cliente.getId());
        assertEquals("12345678901", cliente.getCpf());
        assertEquals("João da Silva", cliente.getNome());
        assertEquals(LocalDate.of(1990, 1, 1), cliente.getDataNascimento());
        assertEquals("123456789", cliente.getTelefone());
        assertEquals(endereco, cliente.getEndereco());
    }
}
