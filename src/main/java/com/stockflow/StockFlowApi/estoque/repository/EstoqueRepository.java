package com.stockflow.StockFlowApi.estoque.repository;

import com.stockflow.StockFlowApi.estoque.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}