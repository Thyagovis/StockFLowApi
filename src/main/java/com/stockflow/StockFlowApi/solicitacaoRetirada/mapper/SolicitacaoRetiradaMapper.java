package com.stockflow.StockFlowApi.solicitacaoRetirada.mapper;

import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao.SolicitacaoRetiradaDetalhadaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao.SolicitacaoRetiradaSimplificadaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.entity.SolicitacaoItemRetirada;
import com.stockflow.StockFlowApi.solicitacaoRetirada.entity.SolicitacaoRetirada;

import java.util.List;

public class SolicitacaoRetiradaMapper {

    public static SolicitacaoRetiradaDetalhadaResponseDTO toDetalhadaRetiradaDTO(
            SolicitacaoRetirada solicitacaoRetirada,
            List<SolicitacaoItemRetirada> itensRetirada
    ){

        return new SolicitacaoRetiradaDetalhadaResponseDTO(
                solicitacaoRetirada.getId(),
                solicitacaoRetirada.getStatusSolicitacao(),
                solicitacaoRetirada.getJustificativa(),
                solicitacaoRetirada.getData(),
                itensRetirada
        );
    }



    public static SolicitacaoRetiradaSimplificadaResponseDTO toSimplesRetiradaDTO(SolicitacaoRetirada solicitacaoRetirada){
        return new SolicitacaoRetiradaSimplificadaResponseDTO(
                solicitacaoRetirada.getId(),
                solicitacaoRetirada.getStatusSolicitacao(),
                solicitacaoRetirada.getJustificativa(),
                solicitacaoRetirada.getData(),
                solicitacaoRetirada.getUsuario().getNome()
        );
    }
}
