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
                produto.getNome(),
                produto.getEstoqueMinimo(),
                produto.getEstoqueMaximo()
        );
    }

    public static ProdutoResponseDTO toResponseDTO(Produto produto) {
        if (produto == null) {
            return null;
        }

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getCodigo(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getEstoqueMinimo(),
                produto.getEstoqueMaximo(),
                CategoriaMapper.toSummaryDTO(produto.getCategoria()),
                produto.getDataCadastro(),
                produto.isAtivo()
        );
    }

}
