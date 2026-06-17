package com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SolicitacaoItemRetiradaUpdateDTO(
        Long produto_id,
        BigDecimal quantidade
) {
}
