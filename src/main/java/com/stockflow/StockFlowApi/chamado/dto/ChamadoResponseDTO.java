package com.stockflow.StockFlowApi.chamado.dto;

import com.stockflow.StockFlowApi.chamado.enums.StatusChamado;
import com.stockflow.StockFlowApi.chamado.enums.TipoChamado;

import java.time.LocalDateTime;
import java.util.List;

public record ChamadoResponseDTO(

        Long id,
        Long usuarioId,
        TipoChamado tipoChamado,
        StatusChamado statusChamado,
        String descricao,
        LocalDateTime dataAbertura,
        LocalDateTime dataFechamento,

        List<ItemChamadoDTO> itens

) {
}