package com.stockflow.StockFlowApi.movimentacao.dto;

import com.stockflow.StockFlowApi.movimentacao.enums.OrigemMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.enums.TipoMovimentacao;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MovimentacaoLoteRequestDTO(

        @NotNull(message = "Não pode ser nulo")
        TipoMovimentacao tipoMovimentacao,

        @NotNull(message = "Não pode ser nulo")
        OrigemMovimentacao origemMovimentacao,

        @NotNull(message = "Não pode ser nulo")
        String observacao,

        @NotEmpty(message = "Não pode ser vazio")
        List<ItemMovimentacaoRequestDTO> itens

) {
}