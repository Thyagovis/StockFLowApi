package com.stockflow.StockFlowApi.chamado.dto;

import com.stockflow.StockFlowApi.chamado.enums.TipoChamado;

import java.util.List;

public record ChamadoRequestDTO(

        Long usuarioId,
        TipoChamado tipoChamado,
        String descricao,

        List<ItemChamadoDTO> itens

) {
}