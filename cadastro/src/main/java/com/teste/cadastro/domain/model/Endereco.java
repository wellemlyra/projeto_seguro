package com.teste.cadastro.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;

@Embeddable
public class Endereco {

    @Size(max = 9)
    private String cep;

    @Size(max = 100)
    private String nomeRua;

    public String getCep() {
        return cep;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }
}