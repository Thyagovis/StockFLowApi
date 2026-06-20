package com.stockflow.StockFlowApi.usuario.dto;

import com.stockflow.StockFlowApi.usuario.enums.Cargo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRegisterDTO(

        @NotBlank(message = "Não deve ser vazio")
        String nome,

        @Email(message = "Deve ser um email valido")
        @NotBlank(message = "Não deve ser vazio")
        String email,

        @NotBlank(message = "Não deve ser vazio")
        String login,

        @NotBlank(message = "Não deve ser vazio")
        String senha,

        @NotNull(message = "Não deve ser vazio")
        Cargo cargo

) {
}
