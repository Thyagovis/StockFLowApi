package com.stockflow.StockFlowApi.categoria.controller;

import com.stockflow.StockFlowApi.categoria.dto.CategoriaRequestDTO;
import com.stockflow.StockFlowApi.categoria.dto.CategoriaResponseDTO;
import com.stockflow.StockFlowApi.categoria.service.CategoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    
    @GetMapping
    public List<CategoriaResponseDTO> listarTodas() {
        return categoriaService.listarTodas();
    }


    @GetMapping("/{id}")
    public CategoriaResponseDTO buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }


    @PostMapping
    public CategoriaResponseDTO criar(@RequestBody @Valid CategoriaRequestDTO dto) {
        return categoriaService.criar(dto);
    }


    @PutMapping("/{id}")
    public CategoriaResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaRequestDTO dto) {

        return categoriaService.atualizar(id, dto);
    }


    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
    }
}