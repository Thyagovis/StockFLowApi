package com.stockflow.StockFlowApi.produto.dto;

import com.stockflow.StockFlowApi.shared.costraints.NullOrNotBlank;
import jakarta.validation.constraints.Positive;


public record ProdutoPatchDTO(

        @NullOrNotBlank(message = "Não pode ser vazio")
        String codigo,

        @NullOrNotBlank(message = "Não pode ser vazio")
        String nome,

        @NullOrNotBlank(message = "Não pode ser vazio")
        String descricao,

        @Positive(message = "Deve ser positivo")
        Long estoqueMinimo,

        @Positive(message = "Deve ser positivo")
        Long estoqueMaximo,

        @Positive(message = "Deve ser positivo")
        Long categoriaId,

        Boolean ativo
) {}