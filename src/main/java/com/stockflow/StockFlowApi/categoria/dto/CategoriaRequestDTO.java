package com.stockflow.StockFlowApi.categoria.dto;

public record CategoriaRequestDTO(
        String name,
        String description,
        boolean isAtivo
){}