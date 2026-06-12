package com.stockflow.StockFlowApi.usuario.repository;

import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import org.springframework.data.jpa.domain.Specification;

public class UsuarioSpec {

    public static Specification<Usuario> comAtivo(Boolean ativo) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (ativo == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("ativo"), ativo);
        };
    }

}
