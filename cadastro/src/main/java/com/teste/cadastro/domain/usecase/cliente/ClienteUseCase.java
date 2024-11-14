package com.teste.cadastro.domain.usecase.cliente;

import com.teste.cadastro.domain.dto.ClienteDTO;
import com.teste.cadastro.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteUseCase {

    public Cliente criarCliente(ClienteDTO clienteDTO);
    public List<Cliente> listarClientes();
    public Cliente atualizarClientePorCpf(String cpf, ClienteDTO clienteDTO);
    void deletarCliente(Long id);
    Cliente buscarClientePorCpf(String cpf);
}
