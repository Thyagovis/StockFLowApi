package com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao;

import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item.ItemSolicitacaoRetiradaCreateRequestDTO;

import java.util.List;

public record SolicitacaoRetiradaRequestDTO(
    Long usuario_id,
    String justificativa,
    List<ItemSolicitacaoRetiradaCreateRequestDTO> listaItens
) {
}
