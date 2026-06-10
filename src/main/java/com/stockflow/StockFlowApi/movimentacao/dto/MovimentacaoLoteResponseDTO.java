package com.stockflow.StockFlowApi.movimentacao.dto;

import com.stockflow.StockFlowApi.movimentacao.enums.OrigemMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.enums.TipoMovimentacao;

import java.time.LocalDateTime;

public record MovimentacaoLoteResponseDTO(

        Long id,
        TipoMovimentacao tipoMovimentacao,
        OrigemMovimentacao origemMovimentacao,
        LocalDateTime data,
        String observacao,
        Long criadoPorId

) {
}