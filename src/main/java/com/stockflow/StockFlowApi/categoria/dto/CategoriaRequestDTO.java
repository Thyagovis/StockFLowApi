package com.stockflow.StockFlowApi.categoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaRequestDTO(
        @NotBlank
        String name,

        String description,

        @NotNull
        boolean isAtivo
){}