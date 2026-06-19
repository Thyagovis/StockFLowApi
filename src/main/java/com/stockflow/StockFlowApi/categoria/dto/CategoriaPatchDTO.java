package com.stockflow.StockFlowApi.categoria.dto;

import com.stockflow.StockFlowApi.shared.costraints.NullOrNotBlank;

public record CategoriaPatchDTO(

        @NullOrNotBlank
        String nome,

        @NullOrNotBlank
        String descricao,

        Boolean ativo
) {
}
