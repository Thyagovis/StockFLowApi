package com.stockflow.StockFlowApi.estoque.dto;

import com.stockflow.StockFlowApi.estoque.entity.Estoque;
import com.stockflow.StockFlowApi.produto.dto.ProdutoMapper;

public class EstoqueMapper {

    public static EstoqueResponseDTO toResponseDTO(Estoque estoque) {
        if (estoque == null) {
            return null;
        }

        return new EstoqueResponseDTO(
                estoque.getId(),
                estoque.getQuantidadeDisponivel(),
                estoque.getQuantidadeReservada(),
                ProdutoMapper.toSummaryDTO(estoque.getProduto()),
                estoque.getDataAtualizacao()
        );
    }

}
