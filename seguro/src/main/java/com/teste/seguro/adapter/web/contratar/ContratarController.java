package com.teste.seguro.adapter.web.contratar;

import com.teste.seguro.domain.enums.SeguroType;
import com.teste.seguro.domain.model.Seguro;
import com.teste.seguro.domain.dto.SeguroResponse;
import com.teste.seguro.domain.usecase.contratarSeguro.ContratarSeguroUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/seguros/contratar")
public class ContratarController {

    private final ContratarSeguroUsecase contratar;

    public ContratarController(ContratarSeguroUsecase contratar) {
        this.contratar = contratar;
    }

    @Operation(summary = "Contratar seguro", description = "Contrata um seguro para um cliente com base no CPF e no tipo de seguro escolhido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seguro contratado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado na API de Cadastro"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao processar a contratação do seguro")
    })
    @PostMapping("/{cpf}")
    public ResponseEntity<SeguroResponse> contratarSeguro(
            @Parameter(description = "CPF do cliente", required = true)
            @PathVariable String cpf,
            @Parameter(description = "Tipo de seguro a ser contratado", required = true)
            @RequestParam SeguroType tipo) {

        try {
            Optional<Seguro> seguro = Optional.ofNullable(contratar.contratarSeguro(cpf, tipo));
            if (seguro.isPresent()) {
                return ResponseEntity.ok(new SeguroResponse(HttpStatus.OK, "Seguro contratado com sucesso!", null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new SeguroResponse(HttpStatus.NOT_FOUND, "Cliente não encontrado na API de Cadastro"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SeguroResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno ao processar a contratação do seguro"));
        }
    }
}
