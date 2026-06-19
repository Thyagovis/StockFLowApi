package com.stockflow.StockFlowApi.estoque.dto;

import jakarta.validation.constraints.Positive;

public record EstoquePatchDTO (

        @Positive
        Long quantidadeDisponivel,

        @Positive
        Long quantidadeReservada
) {

}
