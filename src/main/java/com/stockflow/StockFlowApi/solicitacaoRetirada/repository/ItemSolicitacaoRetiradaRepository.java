package com.stockflow.StockFlowApi.solicitacaoRetirada.repository;

import com.stockflow.StockFlowApi.solicitacaoRetirada.entity.ItemSolicitacaoRetirada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemSolicitacaoRetiradaRepository extends JpaRepository<ItemSolicitacaoRetirada, Long> {

    List<ItemSolicitacaoRetirada> findBySolicitacaoRetiradaId(Long solicitacaoId);

}
