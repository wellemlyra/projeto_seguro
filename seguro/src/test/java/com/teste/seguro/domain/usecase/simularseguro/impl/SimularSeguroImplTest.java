package com.teste.seguro.domain.usecase.simularseguro.impl;

import com.teste.seguro.domain.enums.SeguroType;
import com.teste.seguro.domain.model.Seguro;
import com.teste.seguro.domain.util.BuscarSeguroType;
import com.teste.seguro.infrastructure.CadastroClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SimularSeguroImplTest {

    @Mock
    private CadastroClient cadastroClient;

    @InjectMocks
    private SimularSeguroImpl simularSeguroImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSimularSeguroSucesso() {
        // Arrange
        String cpf = "12345678901";
        SeguroType tipo = SeguroType.BRONZE;
        when(cadastroClient.verificarCliente(cpf)).thenReturn(true);

        // Act
        Optional<Seguro> result = simularSeguroImpl.simularSeguro(cpf, tipo);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(cpf, result.get().getCpf());
        assertEquals(tipo, result.get().getTipo());
        assertEquals(BuscarSeguroType.calcularPreco(tipo), result.get().getPreco());
        verify(cadastroClient, times(1)).verificarCliente(cpf);
    }

    @Test
    void testSimularSeguroClienteNaoEncontrado() {
        // Arrange
        String cpf = "12345678901";
        when(cadastroClient.verificarCliente(cpf)).thenReturn(false);

        // Act
        Optional<Seguro> result = simularSeguroImpl.simularSeguro(cpf, SeguroType.OURO);

        // Assert
        assertTrue(result.isEmpty());
        verify(cadastroClient, times(1)).verificarCliente(cpf);
    }

    @Test
    void testSimularSeguroErroInterno() {
        // Arrange
        String cpf = "12345678901";
        when(cadastroClient.verificarCliente(cpf)).thenThrow(new RuntimeException("Erro de conexão"));

        // Act
        Optional<Seguro> result = simularSeguroImpl.simularSeguro(cpf, SeguroType.PRATA);

        // Assert
        assertTrue(result.isEmpty());
        verify(cadastroClient, times(1)).verificarCliente(cpf);
    }
}
