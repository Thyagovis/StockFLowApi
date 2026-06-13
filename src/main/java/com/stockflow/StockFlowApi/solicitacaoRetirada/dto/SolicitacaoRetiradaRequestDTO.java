package com.stockflow.StockFlowApi.solicitacaoRetirada.dto;

import java.util.List;

public record SolicitacaoRetiradaRequestDTO(
    Long usuario_id,
    String justificativa,
    List<SolicitacaoItemRetiradaRequestDTO> listaItens
) {
}
