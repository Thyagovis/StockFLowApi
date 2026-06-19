package com.stockflow.StockFlowApi.movimentacao.dto;

import com.stockflow.StockFlowApi.movimentacao.entity.ItemMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.entity.MovimentacaoLote;
import com.stockflow.StockFlowApi.produto.dto.ProdutoMapper;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioMapper;

public class MovimentacaoMapper {

    public static MovimentacaoLoteResponseDTO toMovimentacaoResponseDTO(MovimentacaoLote movimentacaoLote) {
        if (movimentacaoLote == null) {
            return null;
        }

        return new MovimentacaoLoteResponseDTO(
                movimentacaoLote.getId(),
                movimentacaoLote.getTipoMovimentacao(),
                movimentacaoLote.getOrigemMovimentacao(),
                movimentacaoLote.getDataRegistro(),
                movimentacaoLote.getObservacao(),
                movimentacaoLote.getItens().stream().map(MovimentacaoMapper::toItemResponseDTO).toList(),
                UsuarioMapper.paraUsuarioSummaryDTO(movimentacaoLote.getUsuario())
        );
    }

    public static ItemMovimentacaoResponseDTO toItemResponseDTO(ItemMovimentacao itemMovimentacao) {
        if (itemMovimentacao == null) {
            return null;
        }

        return new ItemMovimentacaoResponseDTO(
                itemMovimentacao.getId(),
                itemMovimentacao.getQuantidade(),
                itemMovimentacao.getCustoUnitario(),
                itemMovimentacao.getCustoTotal(),
                ProdutoMapper.toSummaryDTO(itemMovimentacao.getProduto())
        );
    }

}
