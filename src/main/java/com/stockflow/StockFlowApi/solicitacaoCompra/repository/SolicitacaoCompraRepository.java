package com.stockflow.StockFlowApi.solicitacaoCompra.repository;

import com.stockflow.StockFlowApi.solicitacaoCompra.entity.SolicitacaoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoCompraRepository extends JpaRepository<SolicitacaoCompra, Long> {

}
