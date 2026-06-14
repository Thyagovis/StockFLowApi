package com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao;

import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item.SolicitacaoItemRetiradaRequestDTO;

import java.util.List;

public record SolicitacaoRetiradaRequestDTO(
    Long usuario_id,
    String justificativa,
    List<SolicitacaoItemRetiradaRequestDTO> listaItens
) {
}
