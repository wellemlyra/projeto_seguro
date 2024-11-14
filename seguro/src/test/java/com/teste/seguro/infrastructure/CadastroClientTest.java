package com.teste.seguro.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CadastroClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CadastroClient cadastroClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cadastroClient = new CadastroClient("http://localhost:8080/api/clientes", restTemplate);
    }

    @Test
    void testVerificarClienteExistente() {
        // Arrange
        String cpf = "12345678901";
        when(restTemplate.getForObject("http://localhost:8080/api/clientes/" + cpf, Object.class))
                .thenReturn(new Object());

        // Act
        boolean result = cadastroClient.verificarCliente(cpf);

        // Assert
        assertTrue(result);
        verify(restTemplate, times(1)).getForObject("http://localhost:8080/api/clientes/" + cpf, Object.class);
    }

    @Test
    void testVerificarClienteNaoExistente() {
        // Arrange
        String cpf = "12345678901";
        when(restTemplate.getForObject("http://localhost:8080/api/clientes/" + cpf, Object.class))
                .thenThrow(new RuntimeException("Cliente não encontrado"));

        // Act
        boolean result = cadastroClient.verificarCliente(cpf);

        // Assert
        assertFalse(result);
        verify(restTemplate, times(1)).getForObject("http://localhost:8080/api/clientes/" + cpf, Object.class);
    }
}
