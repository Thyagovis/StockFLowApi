package com.stockflow.StockFlowApi.movimentacao.dto;

import com.stockflow.StockFlowApi.movimentacao.enums.OrigemMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.enums.TipoMovimentacao;

public record MovimentacaoLoteRequestDTO(

        TipoMovimentacao tipoMovimentacao,
        OrigemMovimentacao origemMovimentacao,
        String observacao,
        Long criadoPorId

) {
}