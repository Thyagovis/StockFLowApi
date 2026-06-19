package com.stockflow.StockFlowApi.categoria.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

@Data
@Entity
@SQLDelete(sql = "UPDATE categoria SET ativo = false WHERE id = ?")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @Column(nullable = false)
    private boolean ativo = true;


    protected Categoria() {}

    public Categoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

}
