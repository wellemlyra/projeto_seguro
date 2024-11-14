package com.teste.cadastro.application.port;

import com.teste.cadastro.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Cliente save(Cliente cliente);
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Optional<Cliente> findByCpf(String cpf);

    void deleteById(Long id);

    boolean existsById(Long id);
}