package com.stockflow.StockFlowApi.estoque.service;

import com.stockflow.StockFlowApi.estoque.dto.EstoqueMapper;
import com.stockflow.StockFlowApi.estoque.dto.EstoqueMovementDTO;
import com.stockflow.StockFlowApi.estoque.dto.EstoquePatchDTO;
import com.stockflow.StockFlowApi.estoque.dto.EstoqueResponseDTO;
import com.stockflow.StockFlowApi.estoque.entity.Estoque;
import com.stockflow.StockFlowApi.estoque.repository.EstoqueRepository;
import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.produto.repository.ProdutoRepository;
import com.stockflow.StockFlowApi.shared.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    public List<EstoqueResponseDTO> listarTodos() {
        System.out.println("Listando todos os estoques");
        return estoqueRepository.findAll()
                .stream()
                .map(EstoqueMapper::toResponseDTO)
                .toList();
    }


    public EstoqueResponseDTO buscarPorId(Long id) {

        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        return EstoqueMapper.toResponseDTO(estoque);
    }


    @Transactional
    public EstoqueResponseDTO movimentarEstoque(@Valid EstoqueMovementDTO dto) {

        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        Estoque estoque = estoqueRepository.findByProdutoId(dto.produtoId())
                .orElseGet(() -> new Estoque(0L, 0L, produto));


        switch (dto.tipoMovimentacao()) {
            case ENTRADA -> estoque.adicionarEstoque(dto.quantidadeMovimentada());
            case SAIDA -> estoque.removerEstoque(dto.quantidadeMovimentada());
            case AJUSTE -> estoque.ajustarEstoque(dto.quantidadeMovimentada());
        }

        return EstoqueMapper.toResponseDTO(estoqueRepository.saveAndFlush(estoque));
    }

    @Transactional
    public EstoqueResponseDTO atualizar(Long id, EstoquePatchDTO dto) {

        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estoque não encontrado"));


        if (dto.quantidadeDisponivel() != null) {
            estoque.setQuantidadeDisponivel(dto.quantidadeDisponivel());
        }

        if (dto.quantidadeReservada() != null) {
            estoque.setQuantidadeReservada(dto.quantidadeReservada());
        }

        return EstoqueMapper.toResponseDTO(estoqueRepository.saveAndFlush(estoque));
    }


    public void deletar(Long id) {

        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        estoqueRepository.delete(estoque);
    }
    
}