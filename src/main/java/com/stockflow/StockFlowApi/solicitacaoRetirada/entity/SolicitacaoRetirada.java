package com.stockflow.StockFlowApi.solicitacaoRetirada.entity;

import com.stockflow.StockFlowApi.shared.enums.StatusSolicitacao;
import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private Usuario usuario;

    @OneToMany(
            mappedBy = "SolicitacaoRetirada",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SolicitacaoItemRetirada> itemSolicitacaoRetirada;

    private LocalDateTime data;
    private String justificativa;



}
