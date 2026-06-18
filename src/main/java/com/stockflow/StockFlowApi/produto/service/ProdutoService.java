package com.stockflow.StockFlowApi.produto.service;

import com.stockflow.StockFlowApi.categoria.entity.Categoria;
import com.stockflow.StockFlowApi.categoria.repository.CategoriaRepository;
import com.stockflow.StockFlowApi.produto.dto.ProdutoRequestDTO;
import com.stockflow.StockFlowApi.produto.dto.ProdutoResponseDTO;
import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.produto.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return toResponseDTO(buscarEntityPorId(id));
    }


    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {

        validar(dto);

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setAtivo(dto.ativo());
        produto.setCategoria(categoria);
        produto.setDataCadastro(LocalDateTime.now());

        return toResponseDTO(produtoRepository.save(produto));
    }


    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {

        validar(dto);

        Produto produto = buscarEntityPorId(id);

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setAtivo(dto.ativo());
        produto.setCategoria(categoria);

        return toResponseDTO(produtoRepository.save(produto));
    }


    public void deletar(Long id) {
        Produto produto = buscarEntityPorId(id);
        produtoRepository.delete(produto);
    }


    private Produto buscarEntityPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
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