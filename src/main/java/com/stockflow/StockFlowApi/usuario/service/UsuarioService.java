package com.stockflow.StockFlowApi.usuario.service;

import com.stockflow.StockFlowApi.shared.exceptions.ConflictException;
import com.stockflow.StockFlowApi.shared.exceptions.NotFoundException;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioMapper;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioPatchDTO;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioRegisterDTO;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioResponseDTO;
import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import com.stockflow.StockFlowApi.usuario.repository.UsuarioRepository;
import com.stockflow.StockFlowApi.usuario.repository.UsuarioSpec;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UsuarioResponseDTO>listAll(Boolean ativo) {
        var spec = Specification.where(UsuarioSpec.comAtivo(ativo));

        return usuarioRepository.findAll(spec).stream()
                .map(UsuarioMapper::paraResponseDTO)
                .toList();
    }

    public UsuarioResponseDTO findById(Long id) {
        return UsuarioMapper.paraResponseDTO(
                usuarioRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Usuario não encontrado"))
        );
    }

    @Transactional
    public UsuarioResponseDTO saveUsuario(UsuarioRegisterDTO registerDTO) {
        var senhaCodificada = passwordEncoder.encode(registerDTO.senha());

        if (usuarioRepository.existsByEmailOrLogin(registerDTO.email(), registerDTO.login())) {
            throw new ConflictException("Usuario existente");
        }

        Usuario usuario = new Usuario(
                registerDTO.nome().toLowerCase(),
                registerDTO.email().trim().toLowerCase(),
                registerDTO.login().trim(),
                senhaCodificada,
                registerDTO.cargo()
        );

        return UsuarioMapper.paraResponseDTO(usuarioRepository.save(usuario));
    }

    @Transactional
    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new NotFoundException("Registro não encontrado");
        }

        usuarioRepository.deleteById(id);
    }

    @Transactional
    public UsuarioResponseDTO patchUsuario(Long id, UsuarioPatchDTO patchDTO) {

        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não foi encontrado"));

        if (patchDTO.nome() != null) {
            usuario.setNome(patchDTO.nome().trim().toLowerCase());
        }

        if (patchDTO.email() != null) {

            if (usuarioRepository.existsByEmailAndIdNot(patchDTO.email(), id)) {
                throw new ConflictException("Email existente");
            }

            usuario.setEmail(patchDTO.email().trim().toLowerCase());
        }

        if (patchDTO.login() != null) {

            if (usuarioRepository.existsByLoginAndIdNot(patchDTO.login(), id)) {
                throw new ConflictException("Login existente");
            }

            usuario.setLogin(patchDTO.login().trim());
        }

        if (patchDTO.senha() != null) {
            var novaSenhaCodificada = passwordEncoder.encode(patchDTO.senha());
            usuario.setSenha(novaSenhaCodificada);
        }

        if (patchDTO.cargo() != null) {
            usuario.setCargo(patchDTO.cargo());
        }

        if (patchDTO.ativo() != null) {
            usuario.setAtivo(patchDTO.ativo());
        }

        var response = usuarioRepository.saveAndFlush(usuario);

        return UsuarioMapper.paraResponseDTO(response);
    }

}
