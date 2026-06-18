package com.stockflow.StockFlowApi.produto.dto;

import java.time.LocalDateTime;

public record ProdutoResponseDTO(
        Long id,
        Long categoriaId,
        String nome,
        String descricao,
        boolean ativo,
        LocalDateTime dataCadastro
) {}