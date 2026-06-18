package com.stockflow.StockFlowApi.estoque.controller;

import com.stockflow.StockFlowApi.estoque.dto.EstoqueRequestDTO;
import com.stockflow.StockFlowApi.estoque.dto.EstoqueResponseDTO;
import com.stockflow.StockFlowApi.estoque.service.EstoqueService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoques")
@RequiredArgsConstructor
public class EstoqueController {

    private final EstoqueService estoqueService;

    @GetMapping
    public List<EstoqueResponseDTO> listarTodos() {
        return estoqueService.listarTodos();
    }


    @GetMapping("/{id}")
    public EstoqueResponseDTO buscarPorId(@PathVariable Long id) {
        return estoqueService.buscarPorId(id);
    }


    @PostMapping
    public EstoqueResponseDTO criar(@RequestBody @Valid EstoqueRequestDTO dto) {
        return estoqueService.criar(dto);
    }


    @PutMapping("/{id}")
    public EstoqueResponseDTO atualizar(
            @PathVariable Long id,
            @Valid
            @RequestBody EstoqueRequestDTO dto) {

        return estoqueService.atualizar(id, dto);
    }


    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        estoqueService.deletar(id);
    }
    
}