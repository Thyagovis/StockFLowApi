package com.stockflow.StockFlowApi.chamado.repository;

import com.stockflow.StockFlowApi.chamado.entity.ItemChamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemChamadoRepository extends JpaRepository<ItemChamado, Long> {

    List<ItemChamado> findByChamadoId(Long chamadoId);

    List<ItemChamado> findByProdutoId(Long produtoId);

}