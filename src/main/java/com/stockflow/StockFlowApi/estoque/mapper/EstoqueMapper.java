package com.stockflow.StockFlowApi.estoque.mapper;

import com.stockflow.StockFlowApi.estoque.dto.EstoqueRequestDTO;
import com.stockflow.StockFlowApi.estoque.dto.EstoqueResponseDTO;
import com.stockflow.StockFlowApi.estoque.entity.Estoque;

public class EstoqueMapper {

    public static Estoque toEntity(EstoqueRequestDTO dto) {

        Estoque estoque = new Estoque();

        estoque.setQuantidadeAtual(dto.quantidadeAtual());
        estoque.setQuantidadeReservada(dto.quantidadeReservada());
        estoque.setEstoqueMinimo(dto.estoqueMinimo());
        estoque.setEstoqueMaximo(dto.estoqueMaximo());

        return estoque;
    }

    public static void updateEntity(Estoque estoque, EstoqueRequestDTO dto) {
        estoque.setQuantidadeAtual(dto.quantidadeAtual());
        estoque.setQuantidadeReservada(dto.quantidadeReservada());
        estoque.setEstoqueMinimo(dto.estoqueMinimo());
        estoque.setEstoqueMaximo(dto.estoqueMaximo());
    }

    public static EstoqueResponseDTO toDTO(Estoque estoque) {

        return new EstoqueResponseDTO(
                estoque.getId(),
                estoque.getQuantidadeAtual(),
                estoque.getQuantidadeReservada(),
                estoque.getEstoqueMinimo(),
                estoque.getEstoqueMaximo(),
                estoque.getUltimaAtualizacao()
        );
    }

}