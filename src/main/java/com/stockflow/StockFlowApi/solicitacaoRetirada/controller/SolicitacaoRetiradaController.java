package com.stockflow.StockFlowApi.solicitacaoRetirada.controller;

import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item.SolicitacaoItemRetiradaRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item.SolicitacaoItemRetiradaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao.SolicitacaoRetiradaDetalhadaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao.SolicitacaoRetiradaRequestDTO;
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

    @PostMapping
    public ResponseEntity<SolicitacaoRetiradaSimplificadaResponseDTO> save(@RequestBody SolicitacaoRetiradaRequestDTO dto){
        return new ResponseEntity<>(solicitacaoRetiradaService.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(solicitacaoRetiradaService.delete(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<SolicitacaoItemRetiradaResponseDTO> putItem(
            @PathVariable Long id,
            @RequestBody SolicitacaoItemRetiradaRequestDTO dto){
        return new ResponseEntity<>(solicitacaoRetiradaService.updateItem(id, dto), HttpStatus.OK);
    }

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<SolicitacaoRetiradaSimplificadaResponseDTO> aprovar(@PathVariable Long id){
        return new ResponseEntity<>(solicitacaoRetiradaService.aprovar(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/rejeitar")
    public ResponseEntity<SolicitacaoRetiradaSimplificadaResponseDTO> rejeitar(@PathVariable Long id){
        return new ResponseEntity<>(solicitacaoRetiradaService.rejeitar(id), HttpStatus.OK);
    }
}
