package com.stockflow.StockFlowApi.produto.mapper;

import com.stockflow.StockFlowApi.produto.dto.ProdutoRequestDTO;
import com.stockflow.StockFlowApi.produto.dto.ProdutoResponseDTO;
import com.stockflow.StockFlowApi.produto.entity.Produto;

public class ProdutoMapper {

    public static Produto toEntity(ProdutoRequestDTO dto) {

        Produto produto = new Produto();

        produto.setName(dto.name());
        produto.setDescription(dto.description());
        produto.setAtivo(dto.ativo());
        
        return produto;
    }

    public static ProdutoResponseDTO toDTO(Produto produto) {

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getCategoria().getId(),
                produto.getName(),
                produto.getDescription(),
                produto.isAtivo(),
                produto.getDataCadastro()
        );
    }
}