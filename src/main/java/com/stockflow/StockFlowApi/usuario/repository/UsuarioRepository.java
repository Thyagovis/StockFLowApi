package com.stockflow.StockFlowApi.usuario.repository;
import com.stockflow.StockFlowApi.usuario.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<User, Long> {
}
