package com.teste.cadastro.adapter.web;

import com.teste.cadastro.domain.dto.ClienteResponse;
import com.teste.cadastro.domain.usecase.cliente.ClienteUseCase;
import com.teste.cadastro.domain.dto.ClienteDTO;
import com.teste.cadastro.domain.model.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "API para cadastro e gestão de clientes")
@RestController
@RequestMapping("/api/clientes")
@Validated
public class ClienteController {

    private final ClienteUseCase clienteService;

    public ClienteController(ClienteUseCase clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Cria um novo cliente", description = "Cria um novo cliente com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Cliente com o CPF fornecido já existe"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public ResponseEntity<ClienteResponse> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente novoCliente = clienteService.criarCliente(clienteDTO);
            ClienteResponse response = new ClienteResponse(HttpStatus.CREATED, "Cliente criado com sucesso", novoCliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            ClienteResponse response = new ClienteResponse(HttpStatus.CONFLICT, e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @Operation(summary = "Lista todos os clientes", description = "Retorna uma lista de todos os clientes cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum cliente encontrado")
    })
    @GetMapping
    public ResponseEntity<ClienteResponse> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes.isEmpty()) {
            ClienteResponse response = new ClienteResponse(HttpStatus.NO_CONTENT, "Nenhum cliente encontrado");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        ClienteResponse response = new ClienteResponse(HttpStatus.OK, "Lista de clientes retornada com sucesso", clientes);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Atualiza um cliente pelo CPF", description = "Atualiza as informações de um cliente com o CPF fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> atualizarClientePorCpf(@PathVariable String cpf, @Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente clienteAtualizado = clienteService.atualizarClientePorCpf(cpf, clienteDTO);
            ClienteResponse response = new ClienteResponse(HttpStatus.OK, "Cliente atualizado com sucesso", clienteAtualizado);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ClienteResponse response = new ClienteResponse(HttpStatus.NOT_FOUND, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @Operation(summary = "Deleta um cliente pelo ID", description = "Remove um cliente do sistema pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponse> deletarCliente(@PathVariable Long id) {
        try {
            clienteService.deletarCliente(id);
            ClienteResponse response = new ClienteResponse(HttpStatus.NO_CONTENT, "Cliente deletado com sucesso");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (RuntimeException e) {
            ClienteResponse response = new ClienteResponse(HttpStatus.NOT_FOUND, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @Operation(summary = "Busca um cliente pelo CPF", description = "Retorna as informações de um cliente com o CPF fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente com o CPF fornecido não encontrado")
    })
    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> buscarClientePorCpf(@PathVariable String cpf) {
        try {
            Cliente cliente = clienteService.buscarClientePorCpf(cpf);
            ClienteResponse response = new ClienteResponse(HttpStatus.OK, "Cliente encontrado com sucesso", cliente);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ClienteResponse response = new ClienteResponse(HttpStatus.NOT_FOUND, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}