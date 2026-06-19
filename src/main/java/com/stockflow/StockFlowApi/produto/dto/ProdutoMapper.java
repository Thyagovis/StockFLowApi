package com.stockflow.StockFlowApi.produto.dto;

import com.stockflow.StockFlowApi.categoria.dto.CategoriaMapper;
import com.stockflow.StockFlowApi.produto.entity.Produto;

public class ProdutoMapper {

    public static ProdutoSummaryDTO toSummaryDTO(Produto produto) {
        if (produto == null) {
            return null;
        }

        return new ProdutoSummaryDTO(
                produto.getId(),
                produto.getNome()
        );
    }

    public static ProdutoResponseDTO toResponseDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getCodigo(),
                produto.getNome(),
                produto.getDescricao(),
                CategoriaMapper.toSummaryDTO(produto.getCategoria()),
                produto.getDataCadastro(),
                produto.isAtivo()
        );
    }

}
