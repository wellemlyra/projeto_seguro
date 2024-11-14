package com.teste.seguro.domain.usecase.contratarSeguro.impl;

import com.teste.seguro.domain.enums.SeguroType;
import com.teste.seguro.domain.model.Seguro;
import com.teste.seguro.infrastructure.CadastroClient;
import com.teste.seguro.infrastructure.repository.SeguroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.teste.seguro.domain.util.BuscarSeguroType.calcularPreco;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContratarSeguroImplTest {

    @Mock
    private CadastroClient cadastroClient;

    @Mock
    private SeguroRepository seguroRepository;

    @InjectMocks
    private ContratarSeguroImpl contratarSeguroImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testContratarSeguroSucesso() {
        // Arrange
        String cpf = "12345678901";
        SeguroType tipo = SeguroType.BRONZE;
        Seguro seguro = new Seguro(cpf, tipo, calcularPreco(tipo));

        when(cadastroClient.verificarCliente(cpf)).thenReturn(true);
        when(seguroRepository.save(any(Seguro.class))).thenReturn(seguro);

        // Act
        Seguro result = contratarSeguroImpl.contratarSeguro(cpf, tipo);

        // Assert
        assertEquals(cpf, result.getCpf());
        assertEquals(tipo, result.getTipo());
        assertEquals(calcularPreco(tipo), result.getPreco());
        verify(cadastroClient, times(1)).verificarCliente(cpf);
        verify(seguroRepository, times(1)).save(any(Seguro.class));
    }

    @Test
    void testContratarSeguroClienteNaoEncontrado() {
        // Arrange
        String cpf = "12345678901";
        when(cadastroClient.verificarCliente(cpf)).thenReturn(false);

        // Act
        Seguro result = contratarSeguroImpl.contratarSeguro(cpf, SeguroType.OURO);

        // Assert
        assertNull(result);
        verify(cadastroClient, times(1)).verificarCliente(cpf);
        verify(seguroRepository, never()).save(any(Seguro.class));
    }

    @Test
    void testContratarSeguroErroInterno() {
        // Arrange
        String cpf = "12345678901";
        when(cadastroClient.verificarCliente(cpf)).thenThrow(new RuntimeException("Erro ao verificar cliente"));

        // Act
        Seguro result = contratarSeguroImpl.contratarSeguro(cpf, SeguroType.PRATA);

        // Assert
        assertNull(result);
        verify(cadastroClient, times(1)).verificarCliente(cpf);
        verify(seguroRepository, never()).save(any(Seguro.class));
    }
}
