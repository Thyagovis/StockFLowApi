package com.stockflow.StockFlowApi.movimentacao.dto;

import com.stockflow.StockFlowApi.movimentacao.enums.OrigemMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.enums.TipoMovimentacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MovimentacaoLoteRequestDTO(

        @NotNull
        TipoMovimentacao tipoMovimentacao,

        @NotNull
        OrigemMovimentacao origemMovimentacao,

        @NotBlank
        String observacao,

        @NotEmpty
        List<ItemMovimentacaoRequestDTO> itens

) {
}