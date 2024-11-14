package com.teste.cadastro.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    @Size(max = 100)
    private String nome;

    private LocalDate dataNascimento;

    @Size(max = 15)
    private String telefone;

    @Embedded
    private Endereco endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull @Size(min = 11, max = 11) String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull @Size(min = 11, max = 11) String cpf) {
        this.cpf = cpf;
    }

    public @NotNull @Size(max = 100) String getNome() {
        return nome;
    }

    public void setNome(@NotNull @Size(max = 100) String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public @Size(max = 15) String getTelefone() {
        return telefone;
    }

    public void setTelefone(@Size(max = 15) String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
