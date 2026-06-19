package com.stockflow.StockFlowApi.produto.repository;

import com.stockflow.StockFlowApi.produto.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByIdAndAtivoTrue(Long id);
    boolean existsByCodigoAndIdNot(String nome, Long id);
    boolean existsByCodigo(String nome);

}