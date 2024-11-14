package com.teste.cadastro.infra.respository;

import com.teste.cadastro.application.port.ClienteRepository;
import com.teste.cadastro.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositoryImpl extends JpaRepository<Cliente, Long>, ClienteRepository {

    @Override
    Optional<Cliente> findByCpf(String cpf);
}