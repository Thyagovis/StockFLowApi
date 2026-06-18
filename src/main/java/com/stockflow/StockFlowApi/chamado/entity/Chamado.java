package com.stockflow.StockFlowApi.chamado.entity;

import com.stockflow.StockFlowApi.chamado.enums.StatusChamado;
import com.stockflow.StockFlowApi.chamado.enums.TipoChamado;
import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated
    @Column(nullable = false)
    private TipoChamado tipoChamado;

    @Enumerated
    @Column(nullable = false)
    private StatusChamado statusChamado;

    @Column(nullable = false)
    private String descricao;

    @CreationTimestamp
    private LocalDateTime dataAbertura;

    private LocalDateTime dataFechamento;
}
