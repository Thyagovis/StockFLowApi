package com.stockflow.StockFlowApi.categoria.repository;

import com.stockflow.StockFlowApi.categoria.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByIdAndAtivoTrue(Long id);
    boolean existsByNomeAndIdNot(String nome, Long id);
    boolean existsByNomeIgnoreCase(String nome);

}