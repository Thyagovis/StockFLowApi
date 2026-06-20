package com.stockflow.StockFlowApi.categoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaCreateDTO(

        @NotBlank(message = "Não pode ser vazio")
        String nome,

        @NotNull(message = "Não pode ser nulo")
        String descricao

) {

}