package com.teste.seguro.domain.usecase.contratarSeguro;

import com.teste.seguro.domain.enums.SeguroType;
import com.teste.seguro.domain.model.Seguro;

public interface ContratarSeguroUsecase {

    /**
     * Contrata um seguro para um cliente existente. Verifica se o cliente está cadastrado
     * e persiste a contratação no banco de dados.
     * @param cpf CPF do cliente
     * @param tipo Tipo de seguro a ser contratado
     * @return Seguro contratado ou null se o cliente não for encontrado
     */
    public Seguro contratarSeguro(String cpf, SeguroType tipo);
}
