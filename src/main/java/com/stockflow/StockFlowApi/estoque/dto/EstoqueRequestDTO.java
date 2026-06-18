package com.stockflow.StockFlowApi.estoque.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EstoqueRequestDTO(

        @NotNull 
        Long produtoId,

        @NotNull @Min(0)
        Long quantidadeAtual,

        @NotNull @Min(0)
        Long quantidadeReservada,

        @NotNull @Min(0)
        Long estoqueMinimo,

        @NotNull @Min(0)
        Long estoqueMaximo
) {}