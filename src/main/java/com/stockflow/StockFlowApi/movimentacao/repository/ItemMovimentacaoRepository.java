package com.stockflow.StockFlowApi.movimentacao.repository;

import com.stockflow.StockFlowApi.movimentacao.entity.ItemMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemMovimentacaoRepository extends JpaRepository<ItemMovimentacao, Long> {

    List<ItemMovimentacao> findByMovimentacaoLoteId(Long movimentacaoLoteId);

    List<ItemMovimentacao> findByProdutoId(Long produtoId);

}