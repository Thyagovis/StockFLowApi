package com.stockflow.StockFlowApi.movimentacao.dto;

import com.stockflow.StockFlowApi.movimentacao.enums.OrigemMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.enums.TipoMovimentacao;

import java.util.List;

public record MovimentacaoLoteRequestDTO(

        TipoMovimentacao tipoMovimentacao,
        OrigemMovimentacao origemMovimentacao,
        String observacao,
        Long criadoPorId,

        List<ItemMovimentacaoDTO> itens

) {
}