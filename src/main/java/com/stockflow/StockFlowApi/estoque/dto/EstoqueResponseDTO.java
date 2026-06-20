package com.stockflow.StockFlowApi.estoque.dto;

import com.stockflow.StockFlowApi.produto.dto.ProdutoSummaryDTO;

import java.time.LocalDateTime;

public record EstoqueResponseDTO(
        Long id,
        Long quantidadeDisponivel,
        Long quantidadeReservada,
        ProdutoSummaryDTO produto,
        LocalDateTime ultimaAtualizacao
) {}