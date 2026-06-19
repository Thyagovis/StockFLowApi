package com.stockflow.StockFlowApi.movimentacao.dto;

import com.stockflow.StockFlowApi.produto.dto.ProdutoSummaryDTO;

import java.math.BigDecimal;

public record ItemMovimentacaoResponseDTO(

        Long id,
        Long quantidade,
        BigDecimal custoUnitario,
        BigDecimal custoTotal,
        ProdutoSummaryDTO produto

) {
}
