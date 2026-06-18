package com.stockflow.StockFlowApi.estoque.service;

import com.stockflow.StockFlowApi.estoque.dto.EstoqueRequestDTO;
import com.stockflow.StockFlowApi.estoque.dto.EstoqueResponseDTO;
import com.stockflow.StockFlowApi.estoque.mapper.EstoqueMapper;
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
                .map(EstoqueMapper::toDTO)
                .toList();
    }

    public EstoqueResponseDTO buscarPorId(Long id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        return EstoqueMapper.toDTO(estoque);
    }

    public EstoqueResponseDTO criar(EstoqueRequestDTO dto) {
        validar(dto);

        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Estoque estoque = EstoqueMapper.toEntity(dto);
        estoque.setProduto(produto);
        estoque.setUltimaAtualizacao(LocalDateTime.now());

        return EstoqueMapper.toDTO(estoqueRepository.save(estoque));
    }

    public EstoqueResponseDTO atualizar(Long id, EstoqueRequestDTO dto) {
        validar(dto);

        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        EstoqueMapper.updateEntity(estoque, dto);
        estoque.setProduto(produto);
        estoque.setUltimaAtualizacao(LocalDateTime.now());

        return EstoqueMapper.toDTO(estoqueRepository.save(estoque));
    }

    public void deletar(Long id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        estoqueRepository.delete(estoque);
    }

    public void adicionarQuantidade(Long produtoId, Long quantidade) {
        Estoque estoque = estoqueRepository.findByProdutoId(produtoId)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() + quantidade);
        estoque.setUltimaAtualizacao(LocalDateTime.now());

        estoqueRepository.save(estoque);
    }

    public void removerQuantidade(Long produtoId, Long quantidade) {
        Estoque estoque = estoqueRepository.findByProdutoId(produtoId)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        if (estoque.getQuantidadeAtual() < quantidade) {
            throw new RuntimeException("Estoque insuficiente");
        }

        estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() - quantidade);
        estoque.setUltimaAtualizacao(LocalDateTime.now());

        estoqueRepository.save(estoque);
    }

    private void validar(EstoqueRequestDTO dto) {
        if (dto.quantidadeReservada() > dto.quantidadeAtual()) {
            throw new RuntimeException("Quantidade reservada não pode ser maior que a quantidade atual");
        }

        if (dto.estoqueMinimo() > dto.estoqueMaximo()) {
            throw new RuntimeException("Estoque mínimo não pode ser maior que estoque máximo");
        }
    }
}