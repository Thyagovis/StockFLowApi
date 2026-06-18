package com.stockflow.StockFlowApi.categoria.service;

import com.stockflow.StockFlowApi.categoria.dto.CategoriaRequestDTO;
import com.stockflow.StockFlowApi.categoria.dto.CategoriaResponseDTO;
import com.stockflow.StockFlowApi.categoria.entity.Categoria;
import com.stockflow.StockFlowApi.categoria.mapper.CategoriaMapper;
import com.stockflow.StockFlowApi.categoria.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<CategoriaResponseDTO> listarTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaMapper::toDTO)
                .toList();
    }


    public CategoriaResponseDTO buscarPorId(Long id) {
       return CategoriaMapper.toDTO(buscarEntityPorId(id));
    }


    public CategoriaResponseDTO criar(CategoriaRequestDTO dto) {
        Categoria categoria = CategoriaMapper.toEntity(dto);
        categoria.setDataCadastro(LocalDateTime.now());
        return CategoriaMapper.toDTO(categoriaRepository.save(categoria));
    }


    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = buscarEntityPorId(id);
        CategoriaMapper.updateEntity(categoria, dto);
        return CategoriaMapper.toDTO(categoriaRepository.save(categoria));
    }


    public void deletar(Long id) {
        Categoria categoria = buscarEntityPorId(id);
        categoriaRepository.delete(categoria);
    }


    private Categoria buscarEntityPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

}