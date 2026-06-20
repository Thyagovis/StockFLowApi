package com.stockflow.StockFlowApi.movimentacao.repository;

import com.stockflow.StockFlowApi.movimentacao.entity.MovimentacaoLote;
import com.stockflow.StockFlowApi.movimentacao.enums.TipoMovimentacao;
import org.springframework.data.jpa.domain.Specification;

public class MovimentacaoLoteSpec {

    public static Specification<MovimentacaoLote> comTipo(TipoMovimentacao tipo) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (tipo == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.and(criteriaBuilder.equal(root.get("tipoMovimentacao"), tipo));
        };
    }

}
