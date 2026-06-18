package com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item;

import java.math.BigDecimal;

public record ItemSolicitacaoRetiradaUpdateDTO(
        Long produto_id,
        BigDecimal quantidade
) {
}
