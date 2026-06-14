package com.stockflow.StockFlowApi.solicitacaoCompra.dto.item;

import java.math.BigDecimal;

public record ItemSolicitacaoCompraRequestDTO(
        Long produto_id,
        BigDecimal quantidadeSolicitada,
        String observacao
) {
}
