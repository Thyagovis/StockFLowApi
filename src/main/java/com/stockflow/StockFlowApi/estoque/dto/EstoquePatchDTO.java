package com.stockflow.StockFlowApi.estoque.dto;

import jakarta.validation.constraints.Min;

public record EstoquePatchDTO (

        @Min(0)
        Long quantidadeDisponivel,

        @Min(0)
        Long quantidadeReservada
) {

}
