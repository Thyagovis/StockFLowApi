package com.stockflow.StockFlowApi.categoria.mapper;

import com.stockflow.StockFlowApi.categoria.dto.CategoriaRequestDTO;
import com.stockflow.StockFlowApi.categoria.dto.CategoriaResponseDTO;
import com.stockflow.StockFlowApi.categoria.entity.Categoria;

public class CategoriaMapper {

    public static Categoria toEntity(CategoriaRequestDTO dto) {

        Categoria categoria = new Categoria();

        categoria.setName(dto.name());
        categoria.setDescription(dto.description());
        categoria.setAtivo(dto.isAtivo());

        return categoria;
    }

    
    public static void updateEntity(Categoria categoria, CategoriaRequestDTO dto) {
        categoria.setName(dto.name());
        categoria.setDescription(dto.description());
        categoria.setAtivo(dto.isAtivo());
    }


    public static CategoriaResponseDTO toDTO(Categoria categoria) {

        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getName(),
                categoria.getDescription(),
                categoria.isAtivo(),
                categoria.getDataCadastro()
        );
    }
}