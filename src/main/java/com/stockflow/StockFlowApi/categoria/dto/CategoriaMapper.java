package com.stockflow.StockFlowApi.categoria.dto;

import com.stockflow.StockFlowApi.categoria.entity.Categoria;

public class CategoriaMapper {

    public static CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }

        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getDataCadastro(),
                categoria.isAtivo()
        );
    }

}
