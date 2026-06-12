package com.stockflow.StockFlowApi.usuario.dto;


import com.stockflow.StockFlowApi.usuario.enums.Cargo;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        Cargo cargo,
        LocalDateTime dataCriacao,
        Boolean ativo
) {
}
