package com.stockflow.StockFlowApi.categoria.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaCreateDTO(

        @NotBlank(message = "Não pode ser vazio")
        String nome,

        @NotBlank(message = "Não pode ser vazio")
        String descricao

) {

}