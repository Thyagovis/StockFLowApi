package com.stockflow.StockFlowApi.solicitacaoRetirada.repository;

import com.stockflow.StockFlowApi.solicitacaoRetirada.entity.SolicitacaoRetirada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoRetiradaRepository  extends JpaRepository<SolicitacaoRetirada, Long> {
}
