package com.teste.seguro.adapter.web.simular;

import com.teste.seguro.domain.enums.SeguroType;
import com.teste.seguro.domain.model.Seguro;
import com.teste.seguro.domain.dto.SeguroResponse;
import com.teste.seguro.domain.usecase.simularseguro.SimularSeguroUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SimularControllerTest {

    @Mock
    private SimularSeguroUsecase simularSeguroUsecase;

    @InjectMocks
    private SimularController simularController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSimularSeguroSucesso() {
        // Arrange
        String cpf = "12345678901";
        SeguroType tipo = SeguroType.BRONZE;
        Seguro seguroMock = new Seguro(); // Mock do seguro simulado
        when(simularSeguroUsecase.simularSeguro(anyString(), any(SeguroType.class)))
                .thenReturn(Optional.of(seguroMock));

        // Act
        ResponseEntity<SeguroResponse> response = simularController.simularSeguro(cpf, tipo);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Simulação de seguro realizada com sucesso", response.getBody().getMessage());
        verify(simularSeguroUsecase, times(1)).simularSeguro(cpf, tipo);
    }

    @Test
    void testSimularSeguroClienteNaoEncontrado() {
        // Arrange
        String cpf = "12345678901";
        SeguroType tipo = SeguroType.OURO;
        when(simularSeguroUsecase.simularSeguro(anyString(), any(SeguroType.class)))
                .thenReturn(Optional.empty());

        // Act
        ResponseEntity<SeguroResponse> response = simularController.simularSeguro(cpf, tipo);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Cliente não encontrado na API de Cadastro", response.getBody().getMessage());
        verify(simularSeguroUsecase, times(1)).simularSeguro(cpf, tipo);
    }
}
