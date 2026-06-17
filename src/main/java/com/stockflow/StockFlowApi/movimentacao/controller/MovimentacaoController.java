package com.stockflow.StockFlowApi.movimentacao.controller;

import com.stockflow.StockFlowApi.movimentacao.dto.MovimentacaoLoteRequestDTO;
import com.stockflow.StockFlowApi.movimentacao.dto.MovimentacaoLoteResponseDTO;
import com.stockflow.StockFlowApi.movimentacao.entity.MovimentacaoLote;
import com.stockflow.StockFlowApi.movimentacao.service.MovimentacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
@AllArgsConstructor
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<MovimentacaoLoteResponseDTO> save(
            @RequestBody MovimentacaoLoteRequestDTO dto) {

        MovimentacaoLote movimentacao = movimentacaoService.save(dto);

        MovimentacaoLoteResponseDTO response =
                movimentacaoService.findById(movimentacao.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoLoteResponseDTO>> listAll() {

        return ResponseEntity.ok(
                movimentacaoService.listAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoLoteResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                movimentacaoService.findById(id)
        );
    }

}