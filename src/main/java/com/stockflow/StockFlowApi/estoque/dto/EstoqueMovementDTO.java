package com.stockflow.StockFlowApi.estoque.dto;

import com.stockflow.StockFlowApi.movimentacao.enums.TipoMovimentacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EstoqueMovementDTO(

        @NotNull(message = "Não pode ser nulo")
        @Positive(message = "Não pode ser negativo")
        Long produtoId,

        @NotNull(message = "Não pode ser nulo")
        Long quantidadeMovimentada,

        @NotNull(message = "Não pode ser nulo")
        TipoMovimentacao tipoMovimentacao

) {

}