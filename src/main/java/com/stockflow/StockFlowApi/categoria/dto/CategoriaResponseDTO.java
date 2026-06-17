package com.stockflow.StockFlowApi.categoria.dto;

import java.time.LocalDateTime;

public record CategoriaResponseDTO(
        Long id,
        String name,
        String description,
        boolean isAtivo,
        LocalDateTime dataCadastro
) {}