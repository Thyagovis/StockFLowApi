package com.stockflow.StockFlowApi.user.entity;

import com.stockflow.StockFlowApi.user.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterDTO(

        @NotBlank
        String name,

        @Email @NotBlank
        String email,

        @NotBlank
        String login,

        @NotBlank
        String password,

        @NotNull
        Role role

) {
}
