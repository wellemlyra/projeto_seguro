package com.teste.seguro.domain.usecase.simularseguro.impl;

import com.teste.seguro.domain.enums.SeguroType;
import com.teste.seguro.domain.model.Seguro;
import com.teste.seguro.domain.usecase.simularseguro.SimularSeguroUsecase;
import com.teste.seguro.domain.util.BuscarSeguroType;
import com.teste.seguro.infrastructure.CadastroClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class SimularSeguroImpl implements SimularSeguroUsecase {

    private final CadastroClient cadastroClient;

    @Override
    public Optional<Seguro> simularSeguro(String cpf, SeguroType tipo) {
        try {
            if (cadastroClient.verificarCliente(cpf)) {
                double preco = BuscarSeguroType.calcularPreco(tipo);
                return Optional.of(new Seguro(cpf, tipo, preco));
            }
        } catch (Exception e) {
            // Fallback ou logging pode ser adicionado aqui
            System.err.println("Erro ao verificar cliente na API de Cadastro: " + e.getMessage());
        }
        return Optional.empty();
    }


}
