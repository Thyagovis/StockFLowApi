package com.stockflow.StockFlowApi.solicitacaoCompra.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemSolicitacaoCompraCreateRequestDTO(
        @NotNull
        Long produto_id,

        @NotNull
        BigDecimal quantidadeSolicitada,

        @NotBlank
        String observacao
) {
}
