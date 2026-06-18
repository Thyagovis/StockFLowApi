package com.stockflow.StockFlowApi.produto.service;

import com.stockflow.StockFlowApi.categoria.entity.Categoria;
import com.stockflow.StockFlowApi.categoria.repository.CategoriaRepository;
import com.stockflow.StockFlowApi.produto.dto.ProdutoRequestDTO;
import com.stockflow.StockFlowApi.produto.dto.ProdutoResponseDTO;
import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.produto.repository.ProdutoRepository;
import com.stockflow.StockFlowApi.shared.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }


    public ProdutoResponseDTO buscarPorId(Long id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        return toResponseDTO(produto);
    }


    @Transactional
    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {

        validar(dto);

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

        var produto = new Produto(
                dto.nome(),
                dto.descricao(),
                categoria
        );

        return toResponseDTO(produtoRepository.save(produto));
    }


    @Transactional
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {

        validar(dto);

        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        var categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setAtivo(dto.ativo());
        produto.setCategoria(categoria);

        return toResponseDTO(produtoRepository.save(produto));
    }


    @Transactional
    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new NotFoundException("Produto não encontrado");
        }

        produtoRepository.deleteById(id);
    }


    private void validar(ProdutoRequestDTO dto) {
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new RuntimeException("Nome do produto é obrigatório");
        }
    }


    private ProdutoResponseDTO toResponseDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getCategoria() != null ? produto.getCategoria().getId() : null,
                produto.getNome(),
                produto.getDescricao(),
                produto.isAtivo(),
                produto.getDataCadastro()
        );
    }

}