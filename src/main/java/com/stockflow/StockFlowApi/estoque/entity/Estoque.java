package com.stockflow.StockFlowApi.estoque.entity;

import com.stockflow.StockFlowApi.produto.entity.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")

    private Produto produto;
    private Long quantidadeAtual;
    private Long quantidadeReservada;
    private Long estoqueMinimo;
    private Long estoqueMaximo;
    private LocalDateTime ultimaAtualizacao;

}
