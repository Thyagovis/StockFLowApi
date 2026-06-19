package com.stockflow.StockFlowApi.movimentacao.entity;

import com.stockflow.StockFlowApi.produto.entity.Produto;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class ItemMovimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false)
    private Long quantidade;

    @Column(nullable = false)
    private BigDecimal custoUnitario;

    @Column(nullable = false)
    private BigDecimal custoTotal;



    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "movimentacao_lote_id")
    private MovimentacaoLote movimentacaoLote;

    protected ItemMovimentacao() {}

    public ItemMovimentacao(Long quantidade, BigDecimal custoUnitario, BigDecimal custoTotal, Produto produto, MovimentacaoLote movimentacaoLote) {
        this.quantidade = quantidade;
        this.custoUnitario = custoUnitario;
        this.custoTotal = custoTotal;
        this.produto = produto;
        this.movimentacaoLote = movimentacaoLote;
    }

}
