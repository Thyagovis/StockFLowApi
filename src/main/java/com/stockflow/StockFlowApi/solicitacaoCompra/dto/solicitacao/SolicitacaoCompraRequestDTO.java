package com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao;

import com.stockflow.StockFlowApi.solicitacaoCompra.dto.item.ItemSolicitacaoCompraCreateRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SolicitacaoCompraRequestDTO(
        @NotNull
        Long usuario_id,

        @NotBlank
        String obs,

        @NotEmpty
        List<ItemSolicitacaoCompraCreateRequestDTO> itensCompra
) {
}
