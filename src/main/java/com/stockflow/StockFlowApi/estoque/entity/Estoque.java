package com.stockflow.StockFlowApi.estoque.entity;

import com.stockflow.StockFlowApi.produto.entity.Produto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(nullable = false)
    private Long quantidadeDisponivel;

    @Column(nullable = false)
    private Long quantidadeReservada;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;

    protected Estoque() {}

    public Estoque(Long quantidadeDisponivel, Long quantidadeReservada, Produto produto) {
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.quantidadeReservada = quantidadeReservada;
        this.produto = produto;
    }

    public void adicionarEstoque(Long quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        this.quantidadeDisponivel += quantidade;
    }

    public void removerEstoque(Long quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (quantidade > quantidadeDisponivel) {
            throw new IllegalArgumentException("Quantidade deve ser menor que quantidade");
        }

        this.quantidadeDisponivel -= quantidade;
    }

    public void reservar(Long quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (quantidade > quantidadeDisponivel) {
            throw new IllegalArgumentException("Quantidade excede o limite disponível");
        }


        this.quantidadeDisponivel -= quantidade;
        this.quantidadeReservada += quantidade;
    }

    public void removerReserva(Long quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (quantidade > quantidadeReservada) {
            throw new IllegalArgumentException("Quantidade excede o limite reservado");
        }

        this.quantidadeReservada -= quantidade;
    }

    public void ajustarEstoque(Long quantidade) {
        if ((this.quantidadeDisponivel + quantidade) < 0) {
            throw new IllegalArgumentException("Operação invalida");
        }

        this.quantidadeDisponivel += quantidade;
    }

}
