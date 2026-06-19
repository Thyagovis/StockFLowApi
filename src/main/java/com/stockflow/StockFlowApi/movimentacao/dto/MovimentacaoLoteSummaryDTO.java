package com.stockflow.StockFlowApi.movimentacao.dto;

import com.stockflow.StockFlowApi.movimentacao.enums.OrigemMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.enums.TipoMovimentacao;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioSummaryDTO;

import java.time.LocalDateTime;

public record MovimentacaoLoteSummaryDTO(

        Long id,
        TipoMovimentacao tipoMovimentacao,
        OrigemMovimentacao origemMovimentacao,
        UsuarioSummaryDTO usuario,
        LocalDateTime dataRegistro

) {
}
