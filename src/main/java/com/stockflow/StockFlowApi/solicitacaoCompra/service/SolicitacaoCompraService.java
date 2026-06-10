package com.stockflow.StockFlowApi.solicitacaoCompra.service;

import com.stockflow.StockFlowApi.shared.enums.StatusSolicitacao;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.SolicitacaoCompraRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.entity.SolicitacaoCompra;
import com.stockflow.StockFlowApi.solicitacaoCompra.repository.SolicitacaoCompraRepository;
import com.stockflow.StockFlowApi.usuario.entity.User;
import com.stockflow.StockFlowApi.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Service
@AllArgsConstructor
@Log4j2
@Transactional
public class SolicitacaoCompraService {

    private final SolicitacaoCompraRepository solicitacaoCompraRepository;
    private final UsuarioRepository usuarioRepository;

    public SolicitacaoCompra save(SolicitacaoCompraRequestDTO dto){

        System.out.println("Chegou no service");

        User user = usuarioRepository
                .findById(dto.usuario_id())
                .orElseThrow();

        SolicitacaoCompra solicitacaoCompra = new SolicitacaoCompra();

        solicitacaoCompra.setUser(user);
        solicitacaoCompra.setData(LocalDateTime.now());
        solicitacaoCompra.setStatusSolicitacao(StatusSolicitacao.ABERTA);
        solicitacaoCompra.setObservacao(dto.obs());

        log.info("Solicitacao data: " + solicitacaoCompra.getData());

        System.out.println("Antes do save");
        solicitacaoCompraRepository.save(solicitacaoCompra);
        System.out.println("Depois do save");
        return solicitacaoCompra;
    }

    public List<SolicitacaoCompra> listAll(){

        return solicitacaoCompraRepository.findAll();

    }


}
