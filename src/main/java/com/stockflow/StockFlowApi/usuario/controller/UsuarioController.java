package com.stockflow.StockFlowApi.usuario.controller;

import com.stockflow.StockFlowApi.usuario.doc.UsuarioControllerDoc;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioPatchDTO;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioRegisterDTO;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioResponseDTO;
import com.stockflow.StockFlowApi.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController implements UsuarioControllerDoc {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll(
            @RequestParam(name = "ativo", required = false) Boolean ativo
    ) {
        var usuarios = usuarioService.listAll(ativo);
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id) {
        var usuario = usuarioService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody @Valid UsuarioRegisterDTO registerDTO) {
        var response = usuarioService.saveUsuario(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> patchById(@PathVariable Long id, @RequestBody @Valid UsuarioPatchDTO patchDTO) {
        var usuario = usuarioService.patchUsuario(id, patchDTO);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }
}
