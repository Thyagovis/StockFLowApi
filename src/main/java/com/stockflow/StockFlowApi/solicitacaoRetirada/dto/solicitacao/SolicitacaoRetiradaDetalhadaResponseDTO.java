package com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao;

import com.stockflow.StockFlowApi.shared.enums.StatusSolicitacao;
import com.stockflow.StockFlowApi.solicitacaoRetirada.entity.ItemSolicitacaoRetirada;

import java.time.LocalDateTime;
import java.util.List;

public record SolicitacaoRetiradaDetalhadaResponseDTO(
        Long id,
        StatusSolicitacao statusSolicitacao,
        String justificativa,
        LocalDateTime data,
        List<ItemSolicitacaoRetirada> listaItens
) {
}
