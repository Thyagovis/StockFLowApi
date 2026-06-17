package com.stockflow.StockFlowApi.movimentacao.dto;

import java.math.BigDecimal;

public record ItemMovimentacaoDTO(

        Long produtoId,
        BigDecimal quantidade,
        BigDecimal custoUnitario

) {
}