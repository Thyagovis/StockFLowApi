package com.stockflow.StockFlowApi.movimentacao.repository;

import com.stockflow.StockFlowApi.movimentacao.entity.MovimentacaoLote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovimentacaoLoteRepository extends JpaRepository<MovimentacaoLote, Long>, JpaSpecificationExecutor<MovimentacaoLote> {

}