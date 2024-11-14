package com.teste.seguro.domain.usecase.contratarSeguro.impl;

import com.teste.seguro.domain.enums.SeguroType;
import com.teste.seguro.domain.model.Seguro;
import com.teste.seguro.domain.usecase.contratarSeguro.ContratarSeguroUsecase;
import com.teste.seguro.infrastructure.CadastroClient;
import com.teste.seguro.infrastructure.repository.SeguroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.teste.seguro.domain.util.BuscarSeguroType.calcularPreco;

@Service
@AllArgsConstructor
public class ContratarSeguroImpl implements ContratarSeguroUsecase {

    private final CadastroClient cadastroClient;
    private final SeguroRepository seguroRepository;

    public Seguro contratarSeguro(String cpf, SeguroType tipo) {
        try {
            if (cadastroClient.verificarCliente(cpf)) {
                Seguro seguro = new Seguro(cpf, tipo, calcularPreco(tipo));
                return seguroRepository.save(seguro);
            }
        } catch (Exception e) {
            System.err.println("Erro ao contratar seguro: " + e.getMessage());
            // Aqui podemos adicionar um mecanismo de re-tentativa ou fallback
        }
        return null;
    }
}
