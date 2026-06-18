package com.stockflow.StockFlowApi.categoria.dto;

import java.time.LocalDateTime;

public record CategoriaResponseDTO(
        Long id,
        String nome,
        String descricao,
        boolean isAtivo,
        LocalDateTime dataCadastro
) {}