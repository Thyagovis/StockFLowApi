package com.stockflow.StockFlowApi.produto.entity;

import com.stockflow.StockFlowApi.categoria.entity.Categoria;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE produto SET ativo = false WHERE id = ?")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private boolean ativo = true;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

    public Produto(String codigo, String nome, String descricao, Categoria categoria) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
    }

}
