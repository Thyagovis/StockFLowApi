package com.stockflow.StockFlowApi.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProdutoCreateDTO(

        @NotBlank
        String codigo,

        @NotBlank
        String nome,

        @NotNull
        String descricao,

        @NotNull @Positive
        Long categoriaId

) {}