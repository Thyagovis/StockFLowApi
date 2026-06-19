package com.stockflow.StockFlowApi.produto.dto;

import com.stockflow.StockFlowApi.produto.entity.Produto;

public class ProdutoMapper {

    public static ProdutoResponseDTO toResponseDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getCodigo(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getCategoria().getId(),
                produto.getDataCadastro(),
                produto.isAtivo()
        );
    }

}
