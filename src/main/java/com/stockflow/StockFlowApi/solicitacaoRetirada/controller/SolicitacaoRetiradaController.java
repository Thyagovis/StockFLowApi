package com.stockflow.StockFlowApi.solicitacaoRetirada.controller;

import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao.SolicitacaoRetiradaDetalhadaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao.SolicitacaoRetiradaSimplificadaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.service.SolicitacaoRetiradaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("retirada")
@AllArgsConstructor
public class SolicitacaoRetiradaController {

    private final SolicitacaoRetiradaService solicitacaoRetiradaService;

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoRetiradaDetalhadaResponseDTO> findByid(@PathVariable Long id){
        return new ResponseEntity<>(solicitacaoRetiradaService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SolicitacaoRetiradaSimplificadaResponseDTO>> findAll(){
        return new ResponseEntity<>(solicitacaoRetiradaService.findAll(), HttpStatus.OK);
    }


}
