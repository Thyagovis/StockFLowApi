package com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item;

import java.math.BigDecimal;

public record SolicitacaoItemRetiradaUpdateRequestDTO(
        Long SolicitacaoItemId,
        Long produto_id,
        BigDecimal quantidade
) {
}
