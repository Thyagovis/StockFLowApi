package com.stockflow.StockFlowApi.chamado.repository;

import com.stockflow.StockFlowApi.chamado.entity.Chamado;
import com.stockflow.StockFlowApi.chamado.enums.StatusChamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    List<Chamado> findByStatusChamado(StatusChamado statusChamado);

    List<Chamado> findByUsuarioId(Long usuarioId);

}