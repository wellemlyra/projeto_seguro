package com.teste.seguro.domain.usecase.simularseguro;

import com.teste.seguro.domain.enums.SeguroType;
import com.teste.seguro.domain.model.Seguro;

import java.util.Optional;

public interface SimularSeguroUsecase {

    /**
     * Simula o preço de um seguro para um cliente com o CPF fornecido e tipo de seguro.
     * @param cpf CPF do cliente
     * @param tipo Tipo de seguro a ser simulado
     * @return Optional contendo o seguro simulado ou vazio se o cliente não for encontrado
     */
    public Optional<Seguro> simularSeguro(String cpf, SeguroType tipo);
}
