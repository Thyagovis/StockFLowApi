package com.stockflow.StockFlowApi.categoria.controller;

import com.stockflow.StockFlowApi.categoria.dto.CategoriaCreateDTO;
import com.stockflow.StockFlowApi.categoria.dto.CategoriaPatchDTO;
import com.stockflow.StockFlowApi.categoria.dto.CategoriaResponseDTO;
import com.stockflow.StockFlowApi.categoria.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarTodas() {
        var response = categoriaService.listarTodas();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {
        var response = categoriaService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criar(
            @RequestBody @Valid CategoriaCreateDTO dto
    ) {
        var response = categoriaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaPatchDTO dto
    ) {
        var response = categoriaService.atualizar(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {
        categoriaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}