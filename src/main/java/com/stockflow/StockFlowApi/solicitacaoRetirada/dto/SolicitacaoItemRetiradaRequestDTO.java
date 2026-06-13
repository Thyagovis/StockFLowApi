package com.stockflow.StockFlowApi.solicitacaoRetirada.dto;

import com.stockflow.StockFlowApi.produto.dto.ProdutoRequestDTO;
import com.stockflow.StockFlowApi.produto.dto.ProdutoResponseDTO;
import com.stockflow.StockFlowApi.produto.entity.Produto;

import java.math.BigDecimal;

public record SolicitacaoItemRetiradaRequestDTO(
        Long produto_id,
        BigDecimal quantidade
) {
}
