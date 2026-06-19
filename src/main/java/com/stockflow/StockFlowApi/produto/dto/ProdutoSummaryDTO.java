package com.stockflow.StockFlowApi.produto.dto;

public record ProdutoSummaryDTO(
        Long id,
        String nome,
        Long estoqueMinimo,
        Long estoqueMaximo
) {
}
