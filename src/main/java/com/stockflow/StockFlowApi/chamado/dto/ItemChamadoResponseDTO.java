package com.stockflow.StockFlowApi.chamado.dto;

import java.math.BigDecimal;

public record ItemChamadoResponseDTO(

        Long id,
        Long chamadoId,
        Long produtoId,
        BigDecimal quantidade,
        String observacao

) {
}