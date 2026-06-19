package com.stockflow.StockFlowApi.movimentacao.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ItemMovimentacaoRequestDTO(

        @NotNull @Positive
        Long produtoId,

        @NotNull
        Long quantidade,

        @NotNull @Positive
        BigDecimal custoUnitario

) {
}