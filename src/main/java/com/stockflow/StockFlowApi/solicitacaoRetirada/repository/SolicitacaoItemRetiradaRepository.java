package com.stockflow.StockFlowApi.solicitacaoRetirada.repository;

import com.stockflow.StockFlowApi.solicitacaoRetirada.entity.SolicitacaoItemRetirada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoItemRetiradaRepository extends JpaRepository<SolicitacaoItemRetirada, Long> {

    List<SolicitacaoItemRetirada> findBySolicitacaoRetiradaId(Long solicitacaoId);

}
