package com.stockflow.StockFlowApi.security.dto;

public record TokenResponseDTO(

        String token,
        Long expireIn

) {
}
