package com.stockflow.StockFlowApi.produto.dto;

import com.stockflow.StockFlowApi.shared.costraints.NullOrNotBlank;
import jakarta.validation.constraints.Positive;

public record ProdutoPatchDTO(

        @NullOrNotBlank
        String codigo,

        @NullOrNotBlank
        String nome,

        @NullOrNotBlank
        String descricao,

        @Positive
        Long categoriaId,

        Boolean ativo
) {}