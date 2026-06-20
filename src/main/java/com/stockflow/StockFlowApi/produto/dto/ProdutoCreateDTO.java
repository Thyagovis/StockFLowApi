package com.stockflow.StockFlowApi.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProdutoCreateDTO(

        @NotBlank(message = "Não pode ser vazio")
        String codigo,

        @NotBlank(message = "Não pode ser vazio")
        String nome,

        @NotNull(message = "Não pode ser nulo")
        String descricao,

        @NotNull(message = "Não pode ser nulo")
        @Positive(message = "Deve ser positivo")
        Long estoqueMaximo,

        @NotNull(message = "Não pode ser nulo")
        @Positive(message = "Deve ser positivo")
        Long estoqueMinimo,

        @NotNull(message = "Não pode ser nulo")
        @Positive(message = "Deve ser positivo")
        Long categoriaId

) {}