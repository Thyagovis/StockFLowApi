package com.stockflow.StockFlowApi.solicitacaoRetirada.mapper;

import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item.ItemSolicitacaoRetiradaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.entity.ItemSolicitacaoRetirada;

public class ItemSolicitacaoRetiradaMapper {

    public static ItemSolicitacaoRetiradaResponseDTO toItemDTO(ItemSolicitacaoRetirada itemSolicitacaoRetirada){
        return new ItemSolicitacaoRetiradaResponseDTO(
                itemSolicitacaoRetirada.getId(),
                itemSolicitacaoRetirada.getProduto().getNome(),
                itemSolicitacaoRetirada.getQuantidade()
        );
    }
}
