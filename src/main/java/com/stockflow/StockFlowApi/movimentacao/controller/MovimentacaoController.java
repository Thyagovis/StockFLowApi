package com.stockflow.StockFlowApi.movimentacao.controller;

import com.stockflow.StockFlowApi.movimentacao.dto.MovimentacaoLoteRequestDTO;
import com.stockflow.StockFlowApi.movimentacao.dto.MovimentacaoLoteResponseDTO;
import com.stockflow.StockFlowApi.movimentacao.dto.MovimentacaoLoteSummaryDTO;
import com.stockflow.StockFlowApi.movimentacao.enums.TipoMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.service.MovimentacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
@AllArgsConstructor
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<MovimentacaoLoteResponseDTO> save(@RequestBody @Valid MovimentacaoLoteRequestDTO dto) {
        var response = movimentacaoService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoLoteResponseDTO>> listAllFull(
            @RequestParam(name = "tipo", required = false) TipoMovimentacao tipo
    ) {
        var response = movimentacaoService.listAllFull(tipo);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/resumo")
    public ResponseEntity<List<MovimentacaoLoteSummaryDTO>> listAllSummary(
            @RequestParam(name = "tipo", required = false) TipoMovimentacao tipo
    ) {
        var response = movimentacaoService.listAllSummary(tipo);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoLoteResponseDTO> findById(@PathVariable Long id) {
        var response = movimentacaoService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}