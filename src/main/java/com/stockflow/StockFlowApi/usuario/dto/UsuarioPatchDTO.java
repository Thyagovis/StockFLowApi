package com.stockflow.StockFlowApi.usuario.dto;

import com.stockflow.StockFlowApi.shared.costraints.NullOrNotBlank;
import com.stockflow.StockFlowApi.usuario.enums.Cargo;
import jakarta.validation.constraints.Email;

public record UsuarioPatchDTO(

        @NullOrNotBlank(message = "Não deve ser vazio")
        String nome,

        @NullOrNotBlank(message = "Não deve ser vazio")
        @Email(message = "Deve ser um email valido")
        String email,

        @NullOrNotBlank(message = "Não deve ser vazio")
        String login,

        @NullOrNotBlank(message = "Não deve ser vazio")
        String senha,

        Cargo cargo,

        Boolean ativo

) {
}
