package com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao;

import com.stockflow.StockFlowApi.shared.enums.StatusSolicitacao;

import java.time.LocalDateTime;

public record SolicitacaoRetiradaSimplificadaResponseDTO(
        Long id,
        StatusSolicitacao statusSolicitacao,
        String justificativa,
        LocalDateTime data,
        String nomeSolicitante
) {
}
