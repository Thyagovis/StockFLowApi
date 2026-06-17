package com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao;

import com.stockflow.StockFlowApi.shared.enums.StatusSolicitacao;

import java.time.LocalDateTime;
import java.util.List;

public record SolicitacaoRetiradaDetalhadaResponseDTO(
        Long id,
        StatusSolicitacao statusSolicitacao,
        String justificativa,
        LocalDateTime data,
        List<com.stockflow.StockFlowApi.solicitacaoRetirada.entity.SolicitacaoItemRetirada> listaItens
) {
}
