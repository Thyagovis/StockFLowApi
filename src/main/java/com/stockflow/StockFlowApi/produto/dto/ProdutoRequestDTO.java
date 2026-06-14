package com.stockflow.StockFlowApi.produto.dto;

public record ProdutoRequestDTO(
        Long categoriaId,
        String name,
        String description,
        boolean ativo
) {}