package com.teste.cadastro.domain.usecase.cliente.impl;

import com.teste.cadastro.domain.dto.ClienteDTO;
import com.teste.cadastro.domain.model.Cliente;
import com.teste.cadastro.application.port.ClienteRepository;
import com.teste.cadastro.domain.model.Endereco;
import com.teste.cadastro.domain.usecase.cliente.ClienteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteUseCaseImpl implements ClienteUseCase {

    private final ClienteRepository clienteRepository;

    public ClienteUseCaseImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Cria um cliente após verificar se o CPF já existe
    @Override
    @Transactional
    public Cliente criarCliente(ClienteDTO clienteDTO) {
        if (clienteRepository.findByCpf(clienteDTO.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Cliente com o CPF fornecido já existe.");
        }

        Cliente cliente = converterDtoParaEntidade(clienteDTO);
        return clienteRepository.save(cliente);
    }

    // Lista todos os clientes cadastrados
    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // Busca um cliente pelo CPF, lançando exceção se não for encontrado
    @Override
    public Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }

    // Atualiza um cliente existente com as informações fornecidas, usando o CPF como referência
    @Override
    @Transactional
    public Cliente atualizarClientePorCpf(String cpf, ClienteDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        // Atualiza os campos conforme os dados do DTO
        clienteExistente.setNome(clienteDTO.getNome());
        clienteExistente.setDataNascimento(clienteDTO.getDataNascimento());
        clienteExistente.setTelefone(clienteDTO.getTelefone());

        // Atualiza o endereço
        Endereco endereco = new Endereco();
        endereco.setCep(clienteDTO.getEndereco().getCep());
        endereco.setNomeRua(clienteDTO.getEndereco().getNomeRua());
        clienteExistente.setEndereco(endereco);

        return clienteRepository.save(clienteExistente);
    }

    // Deleta um cliente pelo ID, lançando exceção se não for encontrado
    @Override
    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado para exclusão.");
        }
        clienteRepository.deleteById(id);
    }

    // Método auxiliar para converter ClienteDTO para Cliente
    private Cliente converterDtoParaEntidade(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setNome(clienteDTO.getNome());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());
        cliente.setTelefone(clienteDTO.getTelefone());

        Endereco endereco = new Endereco();
        endereco.setCep(clienteDTO.getEndereco().getCep());
        endereco.setNomeRua(clienteDTO.getEndereco().getNomeRua());

        cliente.setEndereco(endereco);

        return cliente;
    }
}
