package com.stockflow.StockFlowApi.security.dto;

import com.stockflow.StockFlowApi.usuario.dto.UsuarioSummaryDTO;

public record TokenResponseDTO(

        String accessToken,
        String tokenType,
        Long expiresIn,
        UsuarioSummaryDTO usuario

) {
    public TokenResponseDTO(String accessToken, Long expiresIn, UsuarioSummaryDTO usuario) {
        this(accessToken, "Bearer", expiresIn, usuario);
    }
}
