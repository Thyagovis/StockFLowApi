package com.stockflow.StockFlowApi.produto.dto;

import java.time.LocalDateTime;

public record ProdutoResponseDTO(
        Long id,
        Long categoriaId,
        String name,
        String description,
        boolean ativo,
        LocalDateTime dataCadastro
) {}