package com.teste.seguro.adapter.web.contratar;

import com.teste.seguro.domain.enums.SeguroType;
import com.teste.seguro.domain.model.Seguro;
import com.teste.seguro.domain.dto.SeguroResponse;
import com.teste.seguro.domain.usecase.contratarSeguro.ContratarSeguroUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContratarControllerTest {

    @Mock
    private ContratarSeguroUsecase contratarSeguroUsecase;

    @InjectMocks
    private ContratarController contratarController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testContratarSeguroSucesso() {
        // Arrange
        String cpf = "12345678901";
        SeguroType tipo = SeguroType.BRONZE;
        Seguro seguroMock = new Seguro(); // Mock do seguro que seria retornado
        when(contratarSeguroUsecase.contratarSeguro(anyString(), any(SeguroType.class)))
                .thenReturn(seguroMock);

        // Act
        ResponseEntity<SeguroResponse> response = contratarController.contratarSeguro(cpf, tipo);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Seguro contratado com sucesso!", response.getBody().getMessage());
        verify(contratarSeguroUsecase, times(1)).contratarSeguro(cpf, tipo);
    }

    @Test
    void testContratarSeguroClienteNaoEncontrado() {
        // Arrange
        String cpf = "12345678901";
        SeguroType tipo = SeguroType.PRATA;
        when(contratarSeguroUsecase.contratarSeguro(anyString(), any(SeguroType.class)))
                .thenReturn(null);

        // Act
        ResponseEntity<SeguroResponse> response = contratarController.contratarSeguro(cpf, tipo);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Cliente não encontrado na API de Cadastro", response.getBody().getMessage());
        verify(contratarSeguroUsecase, times(1)).contratarSeguro(cpf, tipo);
    }

    @Test
    void testContratarSeguroErroInterno() {
        // Arrange
        String cpf = "12345678901";
        SeguroType tipo = SeguroType.OURO;
        when(contratarSeguroUsecase.contratarSeguro(anyString(), any(SeguroType.class)))
                .thenThrow(new RuntimeException("Erro inesperado"));

        // Act
        ResponseEntity<SeguroResponse> response = contratarController.contratarSeguro(cpf, tipo);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno ao processar a contratação do seguro", response.getBody().getMessage());
        verify(contratarSeguroUsecase, times(1)).contratarSeguro(cpf, tipo);
    }
}
