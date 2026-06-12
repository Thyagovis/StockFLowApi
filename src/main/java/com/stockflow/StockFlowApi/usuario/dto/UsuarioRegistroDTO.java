package com.stockflow.StockFlowApi.usuario.dto;

import com.stockflow.StockFlowApi.usuario.enums.Cargo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRegistroDTO(

        @NotBlank
        String nome,

        @Email @NotBlank
        String email,

        @NotBlank
        String login,

        @NotBlank
        String senha,

        @NotNull
        Cargo cargo

) {
}
