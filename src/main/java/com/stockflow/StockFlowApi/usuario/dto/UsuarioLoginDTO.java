package com.stockflow.StockFlowApi.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDTO(

        @NotBlank
        String login,

        @NotBlank
        String senha

) {
}
