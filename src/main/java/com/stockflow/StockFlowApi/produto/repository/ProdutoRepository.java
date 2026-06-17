package com.stockflow.StockFlowApi.produto.repository;

import com.stockflow.StockFlowApi.produto.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}