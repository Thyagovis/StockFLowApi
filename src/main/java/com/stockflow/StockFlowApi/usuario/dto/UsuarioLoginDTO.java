package com.stockflow.StockFlowApi.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDTO(

        @NotBlank(message = "Não deve ser vazio")
        String login,

        @NotBlank(message = "Não deve ser vazio")
        String senha

) {
}
