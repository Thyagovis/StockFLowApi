package com.stockflow.StockFlowApi.estoque.controller;

import com.stockflow.StockFlowApi.estoque.dto.EstoquePatchDTO;
import com.stockflow.StockFlowApi.estoque.dto.EstoqueResponseDTO;
import com.stockflow.StockFlowApi.estoque.service.EstoqueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoques")
@RequiredArgsConstructor
public class EstoqueController {

    private final EstoqueService estoqueService;

    @GetMapping
    public ResponseEntity<List<EstoqueResponseDTO>> listarTodos() {
        System.out.println("Listando todos os estoques");
        var response = estoqueService.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> buscarPorId(@PathVariable Long id) {
        var response = estoqueService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid EstoquePatchDTO dto
    ) {
        var response = estoqueService.atualizar(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        estoqueService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}