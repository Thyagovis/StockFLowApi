package com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SolicitacaoItemRetiradaCreateRequestDTO(
        @NotNull
        Long produto_id,

        @NotNull
        BigDecimal quantidade
) {
}
