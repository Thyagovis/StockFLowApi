package com.stockflow.StockFlowApi.categoria.service;

import com.stockflow.StockFlowApi.categoria.dto.CategoriaMapper;
import com.stockflow.StockFlowApi.categoria.dto.CategoriaCreateDTO;
import com.stockflow.StockFlowApi.categoria.dto.CategoriaPatchDTO;
import com.stockflow.StockFlowApi.categoria.dto.CategoriaResponseDTO;
import com.stockflow.StockFlowApi.categoria.entity.Categoria;
import com.stockflow.StockFlowApi.categoria.repository.CategoriaRepository;
import com.stockflow.StockFlowApi.shared.exceptions.ConflictException;
import com.stockflow.StockFlowApi.shared.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<CategoriaResponseDTO> listarTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaMapper::toResponseDTO)
                .toList();
    }


    public CategoriaResponseDTO buscarPorId(Long id) {
        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não foi encontrada"));
        return CategoriaMapper.toResponseDTO(categoria);
    }


    @Transactional
    public CategoriaResponseDTO criar(CategoriaCreateDTO dto) {

        if (categoriaRepository.existsByNomeIgnoreCase(dto.nome())) {
            throw new ConflictException("Nome ja está sendo utilizado");
        }

        Categoria categoria = new Categoria(
                dto.nome().trim().toLowerCase(),
                dto.descricao().trim()
        );

        return CategoriaMapper.toResponseDTO(categoriaRepository.save(categoria));
    }


    @Transactional
    public CategoriaResponseDTO atualizar(Long id, CategoriaPatchDTO dto) {

       var categoria = categoriaRepository.findById(id)
               .orElseThrow(() -> new NotFoundException("Categoria não foi encontrada"));

       if (dto.nome() != null) {
           if (categoriaRepository.existsByNomeAndIdNot(dto.nome(), id)) {
               throw new ConflictException("Nome já está sendo utilizado");
           }
           categoria.setNome(dto.nome().trim().toLowerCase());
       }

       if (dto.descricao() != null) {
           categoria.setDescricao(dto.descricao().trim());
       }

       if (dto.ativo() != null) {
           categoria.setAtivo(dto.ativo());
       }

        return CategoriaMapper.toResponseDTO(categoriaRepository.saveAndFlush(categoria));
    }


    @Transactional
    public void deletar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new NotFoundException("Categoria não foi encontrada");
        }

        categoriaRepository.deleteById(id);
    }
    
}