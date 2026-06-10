package com.stockflow.StockFlowApi.chamado.dto;

import java.math.BigDecimal;

public record ItemChamadoRequestDTO(

        Long chamadoId,
        Long produtoId,
        BigDecimal quantidade,
        String observacao

) {
}