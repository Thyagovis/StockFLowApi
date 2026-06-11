package com.stockflow.StockFlowApi.usuario.repository;

import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    Optional<UserDetails> findByLogin(String login);
    Optional<Usuario> findByIdAndAtivoTrue(Long id);

}
