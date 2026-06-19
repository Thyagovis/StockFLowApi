package com.stockflow.StockFlowApi.movimentacao.entity;

import com.stockflow.StockFlowApi.movimentacao.enums.OrigemMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.enums.TipoMovimentacao;
import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class MovimentacaoLote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrigemMovimentacao origemMovimentacao;

    @Column(nullable = false)
    private String observacao;

    @CreationTimestamp
    private LocalDateTime dataRegistro;

    @OneToMany(
            mappedBy = "movimentacaoLote",
            fetch = FetchType.LAZY
    )
    private List<ItemMovimentacao> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    protected MovimentacaoLote() {}

    public MovimentacaoLote(TipoMovimentacao tipoMovimentacao, OrigemMovimentacao origemMovimentacao, String observacao, Usuario usuario) {
        this.tipoMovimentacao = tipoMovimentacao;
        this.origemMovimentacao = origemMovimentacao;
        this.observacao = observacao;
        this.usuario = usuario;
    }

    public void adicionarItem(ItemMovimentacao  itemMovimentacao) {
        this.itens.add(itemMovimentacao);
    }

}
