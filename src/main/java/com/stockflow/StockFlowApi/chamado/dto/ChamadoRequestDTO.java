package com.stockflow.StockFlowApi.chamado.dto;

import com.stockflow.StockFlowApi.chamado.enums.TipoChamado;

public record ChamadoRequestDTO(

        Long usuarioId,
        TipoChamado tipoChamado,
        String descricao

) {
}