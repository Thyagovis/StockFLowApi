package com.stockflow.StockFlowApi.produto.dto;

public record ProdutoRequestDTO(
        Long categoriaId,
        String nome,
        String descricao
) {}