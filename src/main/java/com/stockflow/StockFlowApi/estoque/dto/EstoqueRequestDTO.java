package com.stockflow.StockFlowApi.estoque.dto;

public record EstoqueRequestDTO(
        Long produtoId,
        Long quantidadeAtual,
        Long quantidadeReservada,
        Long estoqueMinimo,
        Long estoqueMaximo
) {}