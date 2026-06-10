package com.stockflow.StockFlowApi.user.entity;


import com.stockflow.StockFlowApi.user.enums.Role;

import java.time.Instant;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String login,
        Role role,
        Instant creationTimestamp,
        Boolean active
) {
}
