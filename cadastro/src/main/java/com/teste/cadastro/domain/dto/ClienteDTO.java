package com.teste.cadastro.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ClienteDTO {

    @JsonProperty("nome")
    @NotNull(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @JsonProperty("data_nascimento")
    @NotNull(message = "A data de nascimento é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING) // Define o formato de data
    private LocalDate dataNascimento;

    @JsonProperty("cpf")
    @NotNull(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
    private String cpf;

    @JsonProperty("telefone")
    @Size(max = 15, message = "O telefone deve ter no máximo 15 caracteres")
    @Pattern(regexp = "^\\d{10,15}$", message = "O telefone deve conter entre 10 e 15 dígitos numéricos")
    private String telefone;

    @JsonProperty("endereco")
    @NotNull(message = "O endereço é obrigatório")
    private EnderecoDTO endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}