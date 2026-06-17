package com.stockflow.StockFlowApi.categoria.repository;

import com.stockflow.StockFlowApi.categoria.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}