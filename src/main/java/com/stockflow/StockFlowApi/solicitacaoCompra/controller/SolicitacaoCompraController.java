package com.stockflow.StockFlowApi.solicitacaoCompra.controller;

import com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao.SolicitacaoCompraDetalhadaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao.SolicitacaoCompraRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao.SolicitacaoCompraSimplesResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.service.SolicitacaoCompraService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("compra")
@AllArgsConstructor
@Log4j2
public class SolicitacaoCompraController {

    private final SolicitacaoCompraService solicitacaoCompraService;

    @GetMapping
    public ResponseEntity<List<SolicitacaoCompraSimplesResponseDTO>> listAll(){
        return new ResponseEntity<>(solicitacaoCompraService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoCompraDetalhadaResponseDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(solicitacaoCompraService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(solicitacaoCompraService.delete(id), HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<SolicitacaoCompraSimplesResponseDTO> save(@RequestBody SolicitacaoCompraRequestDTO dto){
        return new ResponseEntity<>(solicitacaoCompraService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<SolicitacaoCompraSimplesResponseDTO> aprovar(@PathVariable Long id){

        return new ResponseEntity<>(solicitacaoCompraService.aprovar(id), HttpStatus.OK);

    }

    @PutMapping("/{id}/rejeitar")
    public ResponseEntity<SolicitacaoCompraSimplesResponseDTO> rejeitar(@PathVariable Long id){

        return new ResponseEntity<>(solicitacaoCompraService.rejeitar(id), HttpStatus.OK);

    }

    @PutMapping("/{id}/comprar")
    public ResponseEntity<SolicitacaoCompraSimplesResponseDTO> comprar(@PathVariable Long id){

        return new ResponseEntity<>(solicitacaoCompraService.comprar(id), HttpStatus.OK);

    }
}
