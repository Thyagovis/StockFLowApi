package com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao;

import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item.SolicitacaoItemRetiradaCreateRequestDTO;

import java.util.List;

public record SolicitacaoRetiradaRequestDTO(
    Long usuario_id,
    String justificativa,
    List<SolicitacaoItemRetiradaCreateRequestDTO> listaItens
) {
}
