package com.stockflow.StockFlowApi.movimentacao.dto;

import com.stockflow.StockFlowApi.movimentacao.enums.OrigemMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.enums.TipoMovimentacao;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioSummaryDTO;

import java.time.LocalDateTime;
import java.util.List;

public record MovimentacaoLoteResponseDTO(

        Long id,
        TipoMovimentacao tipoMovimentacao,
        OrigemMovimentacao origemMovimentacao,
        LocalDateTime dataRegistro,
        String observacao,

        List<ItemMovimentacaoResponseDTO> itens,
        UsuarioSummaryDTO usuario

) {
}