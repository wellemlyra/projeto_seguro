package com.teste.cadastro.domain.usecase.cliente.impl;

import com.teste.cadastro.application.port.ClienteRepository;
import com.teste.cadastro.domain.dto.ClienteDTO;
import com.teste.cadastro.domain.dto.EnderecoDTO;
import com.teste.cadastro.domain.model.Cliente;
import com.teste.cadastro.domain.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteUseCaseImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteUseCaseImpl clienteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarClienteComSucesso() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("12345678901");
        clienteDTO.setNome("João Silva");
        clienteDTO.setDataNascimento(LocalDate.of(1990, 10, 10));
        clienteDTO.setTelefone("1234567890");
        clienteDTO.setEndereco(new EnderecoDTO("12345678", "Rua Exemplo"));

        Cliente cliente = new Cliente();
        cliente.setCpf(clienteDTO.getCpf());

        when(clienteRepository.findByCpf(clienteDTO.getCpf())).thenReturn(Optional.empty());
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente novoCliente = clienteUseCase.criarCliente(clienteDTO);

        assertNotNull(novoCliente);
        assertEquals(clienteDTO.getCpf(), novoCliente.getCpf());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testCriarClienteCpfExistente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("12345678901");

        when(clienteRepository.findByCpf(clienteDTO.getCpf())).thenReturn(Optional.of(new Cliente()));

        assertThrows(RuntimeException.class, () -> clienteUseCase.criarCliente(clienteDTO));
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void testBuscarClientePorCpfEncontrado() {
        String cpf = "12345678901";
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);

        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(cliente));

        Cliente clienteEncontrado = clienteUseCase.buscarClientePorCpf(cpf);

        assertNotNull(clienteEncontrado);
        assertEquals(cpf, clienteEncontrado.getCpf());
        verify(clienteRepository, times(1)).findByCpf(cpf);
    }

    @Test
    void testBuscarClientePorCpfNaoEncontrado() {
        String cpf = "12345678901";
        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clienteUseCase.buscarClientePorCpf(cpf));
        verify(clienteRepository, times(1)).findByCpf(cpf);
    }
}
