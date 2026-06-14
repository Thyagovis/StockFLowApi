package com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item;

import com.stockflow.StockFlowApi.produto.dto.ProdutoResponseDTO;

import java.math.BigDecimal;

public record SolicitacaoItemRetiradaResponseDTO(
        Long id,
        String produtoName,
        BigDecimal quantidade
) {
}
