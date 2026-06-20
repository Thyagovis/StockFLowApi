package com.stockflow.StockFlowApi.categoria.dto;

import com.stockflow.StockFlowApi.shared.costraints.NullOrNotBlank;

public record CategoriaPatchDTO(

        @NullOrNotBlank(message = "Não pode ser vazio")
        String nome,

        @NullOrNotBlank(message = "Não pode ser vazio")
        String descricao,

        Boolean ativo
) {
}
