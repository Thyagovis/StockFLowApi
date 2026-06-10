package com.stockflow.StockFlowApi.user.entity;

public class UserMapper {

    public static UserResponseDTO toUserResponseDTO(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getRole(),
                user.getCreationTimestamp(),
                user.isActive()
        );
    }

}
