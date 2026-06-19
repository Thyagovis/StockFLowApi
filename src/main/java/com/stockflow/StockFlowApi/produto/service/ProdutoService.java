package com.stockflow.StockFlowApi.produto.service;

import com.stockflow.StockFlowApi.categoria.entity.Categoria;
import com.stockflow.StockFlowApi.categoria.repository.CategoriaRepository;
import com.stockflow.StockFlowApi.produto.dto.ProdutoCreateDTO;
import com.stockflow.StockFlowApi.produto.dto.ProdutoMapper;
import com.stockflow.StockFlowApi.produto.dto.ProdutoPatchDTO;
import com.stockflow.StockFlowApi.produto.dto.ProdutoResponseDTO;
import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.produto.repository.ProdutoRepository;
import com.stockflow.StockFlowApi.shared.exceptions.ConflictException;
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
                .map(ProdutoMapper::toResponseDTO)
                .toList();
    }


    public ProdutoResponseDTO buscarPorId(Long id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        return ProdutoMapper.toResponseDTO(produto);
    }


    @Transactional
    public ProdutoResponseDTO criar(ProdutoCreateDTO dto) {

        if (produtoRepository.existsByCodigo(dto.nome().trim())) {
            throw new ConflictException("Produto existente ou inativo");
        }

        Categoria categoria = categoriaRepository.findByIdAndAtivoTrue(dto.categoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

        var produto = new Produto(
                dto.codigo().trim(),
                dto.nome().trim().toLowerCase(),
                dto.descricao().trim().toLowerCase(),
                categoria
        );

        return ProdutoMapper.toResponseDTO(produtoRepository.save(produto));
    }


    @Transactional
    public ProdutoResponseDTO atualizar(Long id, ProdutoPatchDTO dto) {

        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        if (dto.codigo() != null) {
            if (produtoRepository.existsByCodigoAndIdNot(dto.codigo().trim(), id)) {
                throw new ConflictException("Código já registrado");
            }

            produto.setCodigo(dto.codigo().trim());
        }

        if (dto.nome() != null) {
            produto.setNome(dto.nome().trim().toLowerCase());
        }

        if (dto.descricao() != null) {
            produto.setDescricao(dto.descricao().trim().toLowerCase());
        }

        if (dto.categoriaId() != null) {
            var categoria = categoriaRepository.findByIdAndAtivoTrue(dto.categoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

            produto.setCategoria(categoria);
        }

        if (dto.ativo() != null) {
            produto.setAtivo(dto.ativo());
        }

        return ProdutoMapper.toResponseDTO(produtoRepository.save(produto));
    }


    @Transactional
    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new NotFoundException("Produto não encontrado");
        }

        produtoRepository.deleteById(id);
    }

}