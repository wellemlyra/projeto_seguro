package com.teste.cadastro.domain.dto;

import jakarta.validation.constraints.NotEmpty;


public class EnderecoDTO {

    @NotEmpty(message = "O CEP é obrigatório")
    private String cep;

    @NotEmpty(message = "O nome da rua é obrigatório")
    private String nomeRua;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public EnderecoDTO(String cep, String nomeRua) {
        this.cep = cep;
        this.nomeRua = nomeRua;
    }
}
