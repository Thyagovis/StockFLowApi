package com.stockflow.StockFlowApi.estoque.entity;

import com.stockflow.StockFlowApi.produto.entity.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(nullable = false)
    private Long quantidadeAtual;

    @Column(nullable = false)
    private Long quantidadeReservada;

    @Column(nullable = false)
    private Long estoqueMinimo;

    @Column(nullable = false)
    private Long estoqueMaximo;

    @Column(nullable = false)
    private LocalDateTime ultimaAtualizacao;

}
