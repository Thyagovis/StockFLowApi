package com.stockflow.StockFlowApi.solicitacaoRetirada.entity;

import com.stockflow.StockFlowApi.shared.enums.StatusSolicitacao;
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
public class SolicitacaoRetirada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private StatusSolicitacao statusSolicitacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    private LocalDateTime data;
    private String justificativa;



}
