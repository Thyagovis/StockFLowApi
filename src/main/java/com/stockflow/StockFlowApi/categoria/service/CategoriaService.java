package com.stockflow.StockFlowApi.categoria.service;

import com.stockflow.StockFlowApi.categoria.dto.CategoriaRequestDTO;
import com.stockflow.StockFlowApi.categoria.dto.CategoriaResponseDTO;
import com.stockflow.StockFlowApi.categoria.entity.Categoria;
import com.stockflow.StockFlowApi.categoria.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.stockflow.StockFlowApi.shared.utils.StringUtils.stringSaveFormat;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<CategoriaResponseDTO> listarTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }


    public CategoriaResponseDTO buscarPorId(Long id) {
        return toResponseDTO(buscarEntityPorId(id));
    }


    public CategoriaResponseDTO criar(CategoriaRequestDTO dto) {

        validar(dto);

        Categoria categoria = new Categoria();
        categoria.setNome(stringSaveFormat(dto.nome()));
        categoria.setDescricao(dto.descricao());
        categoria.setAtivo(true);
        categoria.setDataCadastro(LocalDateTime.now());

        return toResponseDTO(categoriaRepository.save(categoria));
    }


    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {

        validar(dto);

        Categoria categoria = buscarEntityPorId(id);

        categoria.setNome(stringSaveFormat(dto.nome()));
        categoria.setDescricao(dto.descricao());

        return toResponseDTO(categoriaRepository.save(categoria));
    }


    public void deletar(Long id) {
        Categoria categoria = buscarEntityPorId(id);
        categoriaRepository.delete(categoria);
    }


    private Categoria buscarEntityPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }


    private void validar(CategoriaRequestDTO dto) {
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new RuntimeException("Nome da categoria é obrigatório");
        }
    }


    private CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.isAtivo(),
                categoria.getDataCadastro()
        );
    }
    
}