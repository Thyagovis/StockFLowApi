package com.stockflow.StockFlowApi.usuario.dto;

import com.stockflow.StockFlowApi.usuario.entity.Usuario;

public class UsuarioMapper {

    public static UsuarioResponseDTO paraResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getCargo(),
                usuario.getDataCriacao(),
                usuario.isAtivo()
        );
    }

}
