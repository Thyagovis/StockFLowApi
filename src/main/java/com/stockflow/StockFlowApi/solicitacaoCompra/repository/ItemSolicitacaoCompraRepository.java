package com.stockflow.StockFlowApi.solicitacaoCompra.repository;

import com.stockflow.StockFlowApi.solicitacaoCompra.entity.ItemSolicitacaoCompra;
import com.stockflow.StockFlowApi.solicitacaoRetirada.entity.SolicitacaoItemRetirada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemSolicitacaoCompraRepository extends JpaRepository<ItemSolicitacaoCompra, Long> {

    List<ItemSolicitacaoCompra> findBySolicitacaoCompraId(Long solicitacaoId);

}
