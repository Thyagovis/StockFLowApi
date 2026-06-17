package com.stockflow.StockFlowApi.solicitacaoCompra.mapper;

import com.stockflow.StockFlowApi.solicitacaoCompra.dto.item.ItemSolicitacaoCompraResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.entity.ItemSolicitacaoCompra;

public class ItemSolicitacaoCompraMapper {

    public static ItemSolicitacaoCompraResponseDTO toItemDTO(ItemSolicitacaoCompra itemSolicitacaoCompra){
        return new ItemSolicitacaoCompraResponseDTO(
                itemSolicitacaoCompra.getId(),
                itemSolicitacaoCompra.getProduto().getName(),
                itemSolicitacaoCompra.getQuantidadeSolicitada()
        );
    }

}
