package com.stockflow.StockFlowApi.solicitacaoCompra.mapper;

import com.stockflow.StockFlowApi.solicitacaoCompra.dto.item.ItemSolicitacaoCompraResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao.SolicitacaoCompraDetalhadaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao.SolicitacaoCompraSimplesResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.entity.ItemSolicitacaoCompra;
import com.stockflow.StockFlowApi.solicitacaoCompra.entity.SolicitacaoCompra;
import com.stockflow.StockFlowApi.solicitacaoCompra.repository.ItemSolicitacaoCompraRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SolicitacaoCompraMapper {

    private final ItemSolicitacaoCompraRepository itemSolicitacaoCompraRepository;

    public static SolicitacaoCompraSimplesResponseDTO toSimplesDTO(SolicitacaoCompra solicitacaoCompra){
        return new SolicitacaoCompraSimplesResponseDTO(
                solicitacaoCompra.getId(),
                solicitacaoCompra.getStatusSolicitacao(),
                solicitacaoCompra.getObservacao(),
                solicitacaoCompra.getData()
        );
    }


    public static SolicitacaoCompraDetalhadaResponseDTO toDetalhadoDTO
            (SolicitacaoCompra solicitacaoCompra,
             List<ItemSolicitacaoCompra> listaItens
             ){

        List<ItemSolicitacaoCompraResponseDTO> listaItensDto = listaItens.
                stream()
                .map(ItemSolicitacaoCompraMapper::toItemDTO)
                .toList();

        return new SolicitacaoCompraDetalhadaResponseDTO(
                solicitacaoCompra.getId(),
                solicitacaoCompra.getStatusSolicitacao(),
                solicitacaoCompra.getObservacao(),
                solicitacaoCompra.getData(),
                listaItensDto
        );
    }

}
