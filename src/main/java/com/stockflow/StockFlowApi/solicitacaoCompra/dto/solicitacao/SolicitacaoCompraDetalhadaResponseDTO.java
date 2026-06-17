package com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao;

import com.stockflow.StockFlowApi.shared.enums.StatusSolicitacao;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.item.ItemSolicitacaoCompraResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.entity.ItemSolicitacaoCompra;

import java.time.LocalDateTime;
import java.util.List;

public record SolicitacaoCompraDetalhadaResponseDTO(
        Long id,
        StatusSolicitacao statusSolicitacao,
        String observacao,
        LocalDateTime dataCriacao,
        List<ItemSolicitacaoCompraResponseDTO> listaItens
) {
}
