package com.stockflow.StockFlowApi.solicitacaoRetirada.dto;

import com.stockflow.StockFlowApi.produto.dto.ProdutoResponseDTO;
import com.stockflow.StockFlowApi.produto.entity.Produto;

import java.math.BigDecimal;

public record SolicitacaoItemRetiradaResponseDTO(
        Long id,
        ProdutoResponseDTO produto,
        BigDecimal quantidade
) {
}
