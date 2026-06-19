package com.stockflow.StockFlowApi.produto.dto;

import com.stockflow.StockFlowApi.categoria.dto.CategoriaSummaryDTO;

import java.time.LocalDateTime;

public record ProdutoResponseDTO(
        Long id,
        String codigo,
        String nome,
        String descricao,
        Long estoqueMinimo,
        Long estoqueMaximo,
        CategoriaSummaryDTO categoria,
        LocalDateTime dataCadastro,
        boolean ativo
) {

}