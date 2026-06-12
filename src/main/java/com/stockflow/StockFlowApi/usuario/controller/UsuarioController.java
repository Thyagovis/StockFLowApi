package com.stockflow.StockFlowApi.usuario.controller;

import com.stockflow.StockFlowApi.usuario.dto.UsuarioResponseDTO;
import com.stockflow.StockFlowApi.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll(
            @RequestParam(required = false) Boolean ativo
    ) {
        var usuarios = usuarioService.listAll(ativo);
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
