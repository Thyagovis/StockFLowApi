package com.stockflow.StockFlowApi.chamado.dto;

import java.math.BigDecimal;

public record ItemChamadoDTO(

        Long produtoId,
        BigDecimal quantidade,
        String observacao

) {
}