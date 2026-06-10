package com.stockflow.StockFlowApi.movimentacao.dto;

import java.math.BigDecimal;

public record ItemMovimentacaoResponseDTO(

        Long id,
        Long movimentacaoLoteId,
        Long produtoId,
        BigDecimal quantidade,
        BigDecimal custoUnitario,
        BigDecimal custoTotal

) {
}