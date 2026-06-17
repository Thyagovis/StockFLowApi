package com.stockflow.StockFlowApi.estoque.dto;

import java.time.LocalDateTime;

public record EstoqueResponseDTO(
        Long id,
        Long quantidadeAtual,
        Long quantidadeReservada,
        Long estoqueMinimo,
        Long estoqueMaximo,
        LocalDateTime ultimaAtualizacao
) {}