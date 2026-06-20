package com.stockflow.StockFlowApi.movimentacao.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ItemMovimentacaoRequestDTO(

        @NotNull(message = "Não pode ser nulo")
        @Positive(message = "Deve ser positivo")
        Long produtoId,

        @NotNull(message = "Não pode ser nulo")
        Long quantidade,

        @NotNull(message = "Não pode ser nulo")
        @Positive(message = "Deve ser positivo")
        BigDecimal custoUnitario

) {
}