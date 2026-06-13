package com.stockflow.StockFlowApi.solicitacaoRetirada.entity;

import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.solicitacaoCompra.entity.SolicitacaoCompra;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SolicitacaoItemRetirada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitacao_retirada_id")
    private SolicitacaoRetirada solicitacaoRetirada;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private BigDecimal quantidade;
}
