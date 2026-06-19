package com.stockflow.StockFlowApi.produto.controller;

import com.stockflow.StockFlowApi.produto.dto.ProdutoCreateDTO;
import com.stockflow.StockFlowApi.produto.dto.ProdutoPatchDTO;
import com.stockflow.StockFlowApi.produto.dto.ProdutoResponseDTO;
import com.stockflow.StockFlowApi.produto.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        var response = produtoService.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        var response =  produtoService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(
            @RequestBody @Valid ProdutoCreateDTO dto
    ) {
        var response = produtoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProdutoPatchDTO dto
    ) {
        var response = produtoService.atualizar(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}