package com.stockflow.StockFlowApi.estoque.dto;

import jakarta.validation.constraints.Positive;

public record EstoquePatchDTO (

        @Positive(message = "Deve ser positivo")
        Long quantidadeDisponivel,

        @Positive(message = "Deve ser positivo")
        Long quantidadeReservada
) {

}
