package com.stockflow.StockFlowApi.usuario.service;

import com.stockflow.StockFlowApi.usuario.dto.UsuarioMapper;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioResponseDTO;
import com.stockflow.StockFlowApi.usuario.repository.UsuarioRepository;
import com.stockflow.StockFlowApi.usuario.repository.UsuarioSpec;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDTO>listAll(Boolean ativo) {
        var spec = Specification.where(UsuarioSpec.comAtivo(ativo));

        return usuarioRepository.findAll(spec).stream()
                .map(UsuarioMapper::paraResponseDTO)
                .toList();
    }

    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

}
