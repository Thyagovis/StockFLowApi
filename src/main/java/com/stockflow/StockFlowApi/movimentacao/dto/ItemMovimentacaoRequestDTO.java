package com.stockflow.StockFlowApi.movimentacao.dto;

import java.math.BigDecimal;

public record ItemMovimentacaoRequestDTO(

        Long movimentacaoLoteId,
        Long produtoId,
        BigDecimal quantidade,
        BigDecimal custoUnitario

) {
}