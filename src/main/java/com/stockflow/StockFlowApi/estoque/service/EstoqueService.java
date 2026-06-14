package com.stockflow.StockFlowApi.estoque.service;

import com.stockflow.StockFlowApi.estoque.dto.EstoqueRequestDTO;
import com.stockflow.StockFlowApi.estoque.dto.EstoqueResponseDTO;
import com.stockflow.StockFlowApi.estoque.entity.Estoque;
import com.stockflow.StockFlowApi.estoque.repository.EstoqueRepository;
import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.produto.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    public List<EstoqueResponseDTO> listarTodos() {

        return estoqueRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }


    public EstoqueResponseDTO buscarPorId(Long id) {

        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        return toResponseDTO(estoque);
    }


    public EstoqueResponseDTO criar(EstoqueRequestDTO dto) {

        validar(dto);

        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Estoque estoque = new Estoque();

        estoque.setProduto(produto);
        estoque.setQuantidadeAtual(dto.quantidadeAtual());
        estoque.setQuantidadeReservada(dto.quantidadeReservada());
        estoque.setEstoqueMinimo(dto.estoqueMinimo());
        estoque.setEstoqueMaximo(dto.estoqueMaximo());
        estoque.setUltimaAtualizacao(LocalDateTime.now());

        Estoque salvo = estoqueRepository.save(estoque);

        return toResponseDTO(salvo);
    }


    public EstoqueResponseDTO atualizar(Long id, EstoqueRequestDTO dto) {

        validar(dto);

        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        estoque.setProduto(produto);
        estoque.setQuantidadeAtual(dto.quantidadeAtual());
        estoque.setQuantidadeReservada(dto.quantidadeReservada());
        estoque.setEstoqueMinimo(dto.estoqueMinimo());
        estoque.setEstoqueMaximo(dto.estoqueMaximo());
        estoque.setUltimaAtualizacao(LocalDateTime.now());

        Estoque atualizado = estoqueRepository.save(estoque);

        return toResponseDTO(atualizado);
    }


    public void deletar(Long id) {

        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        estoqueRepository.delete(estoque);
    }


    private void validar(EstoqueRequestDTO dto) {

        if (dto.quantidadeAtual() < 0) {
            throw new RuntimeException("Quantidade atual não pode ser negativa");
        }

        if (dto.quantidadeReservada() < 0) {
            throw new RuntimeException("Quantidade reservada não pode ser negativa");
        }

        if (dto.quantidadeReservada() > dto.quantidadeAtual()) {
            throw new RuntimeException("Quantidade reservada não pode ser maior que a quantidade atual");
        }

        if (dto.estoqueMinimo() > dto.estoqueMaximo()) {
            throw new RuntimeException("Estoque mínimo não pode ser maior que estoque máximo");
        }
    }


    private EstoqueResponseDTO toResponseDTO(Estoque estoque) {

        return new EstoqueResponseDTO(
                estoque.getId(),
                estoque.getQuantidadeAtual(),
                estoque.getQuantidadeReservada(),
                estoque.getEstoqueMinimo(),
                estoque.getEstoqueMaximo(),
                estoque.getUltimaAtualizacao()
        );
    }
    
}