package com.teste.seguro.domain.model;

import com.teste.seguro.domain.enums.SeguroType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "seguros")
public class Seguro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeguroType tipo;

    @Column(nullable = false)
    private double preco;

    @Column(name = "data_contratacao")
    private LocalDate dataContratacao;

    public Seguro(String cpf, SeguroType tipo, double preco) {
        this.cpf = cpf;
        this.tipo = tipo;
        this.preco = preco;
        this.dataContratacao = LocalDate.now(); // Data atual como data de contratação
    }

    // Construtor padrão para JPA
    public Seguro() {}

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public SeguroType getTipo() {
        return tipo;
    }

    public void setTipo(SeguroType tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }
}
