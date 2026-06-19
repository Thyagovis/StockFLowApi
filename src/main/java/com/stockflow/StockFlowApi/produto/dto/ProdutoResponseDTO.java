package com.stockflow.StockFlowApi.produto.dto;

import java.time.LocalDateTime;

public record ProdutoResponseDTO(
        Long id,
        String codigo,
        String nome,
        String descricao,
        Long categoriaId,
        LocalDateTime dataCadastro,
        boolean ativo
) {

}