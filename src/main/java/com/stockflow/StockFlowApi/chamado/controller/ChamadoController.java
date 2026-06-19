package com.stockflow.StockFlowApi.chamado.controller;

import com.stockflow.StockFlowApi.chamado.dto.ChamadoRequestDTO;
import com.stockflow.StockFlowApi.chamado.dto.ChamadoResponseDTO;
import com.stockflow.StockFlowApi.chamado.service.ChamadoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamados")
@AllArgsConstructor
public class ChamadoController {

    private final ChamadoService chamadoService;

    @PostMapping
    public ResponseEntity<ChamadoResponseDTO> save(
            @Valid @RequestBody ChamadoRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(chamadoService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoResponseDTO>> listAll() {

        return ResponseEntity.ok(
                chamadoService.listAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                chamadoService.findById(id)
        );
    }

}