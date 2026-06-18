package com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item;

import java.math.BigDecimal;

public record ItemSolicitacaoRetiradaResponseDTO(
        Long id,
        String produtoNome,
        BigDecimal quantidade
) {
}
