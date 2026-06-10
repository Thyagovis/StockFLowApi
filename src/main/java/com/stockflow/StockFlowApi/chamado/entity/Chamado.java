package com.stockflow.StockFlowApi.chamado.entity;

import com.stockflow.StockFlowApi.chamado.enums.StatusChamado;
import com.stockflow.StockFlowApi.chamado.enums.TipoChamado;
import com.stockflow.StockFlowApi.usuario.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chamado {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    @Enumerated
    private TipoChamado tipoChamado;

    @Enumerated
    private StatusChamado statusChamado;

    private String descricao;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;


}
