package com.stockflow.StockFlowApi.solicitacaoCompra.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemSolicitacaoCompraResponseDTO(
        Long id,
        String produtoNome,
        BigDecimal quantidadeSolicitada
) {
}
