package com.teste.seguro.adapter.web.simular;

import com.teste.seguro.domain.enums.SeguroType;
import com.teste.seguro.domain.model.Seguro;
import com.teste.seguro.domain.dto.SeguroResponse;
import com.teste.seguro.domain.usecase.simularseguro.SimularSeguroUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/seguros/simular")
public class SimularController {

    private final SimularSeguroUsecase simularSeguro;

    public SimularController(SimularSeguroUsecase simularSeguro) {
        this.simularSeguro = simularSeguro;
    }

    @Operation(summary = "Simular seguro", description = "Simula o preço de um seguro com base no CPF do cliente e no tipo de seguro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Simulação de seguro realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado na API de Cadastro")
    })
    @GetMapping("/{cpf}")
    public ResponseEntity<SeguroResponse> simularSeguro(
            @Parameter(description = "CPF do cliente", required = true)
            @PathVariable String cpf,
            @Parameter(description = "Tipo de seguro a ser simulado", required = true)
            @RequestParam SeguroType tipo) {

        Optional<Seguro> simulado = simularSeguro.simularSeguro(cpf, tipo);
        return simulado.map(seguro ->
                ResponseEntity.ok(new SeguroResponse(HttpStatus.OK, "Simulação de seguro realizada com sucesso", seguro))
        ).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new SeguroResponse(HttpStatus.NOT_FOUND, "Cliente não encontrado na API de Cadastro"))
        );
    }
}