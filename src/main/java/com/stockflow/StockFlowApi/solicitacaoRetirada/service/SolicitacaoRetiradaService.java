package com.stockflow.StockFlowApi.solicitacaoRetirada.service;

import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.produto.repository.ProdutoRepository;
import com.stockflow.StockFlowApi.shared.enums.StatusSolicitacao;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item.SolicitacaoItemRetiradaRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item.SolicitacaoItemRetiradaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item.SolicitacaoItemRetiradaUpdateRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao.SolicitacaoRetiradaRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao.SolicitacaoRetiradaDetalhadaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao.SolicitacaoRetiradaSimplificadaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.entity.SolicitacaoItemRetirada;
import com.stockflow.StockFlowApi.solicitacaoRetirada.entity.SolicitacaoRetirada;
import com.stockflow.StockFlowApi.solicitacaoRetirada.repository.SolicitacaoItemRetiradaRepository;
import com.stockflow.StockFlowApi.solicitacaoRetirada.repository.SolicitacaoRetiradaRepository;
import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import com.stockflow.StockFlowApi.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Data
@Transactional
public class SolicitacaoRetiradaService {

    private final SolicitacaoRetiradaRepository solicitacaoRetiradaRepository;
    private final SolicitacaoItemRetiradaRepository solicitacaoItemRetiradaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;



    private SolicitacaoRetiradaDetalhadaResponseDTO definirDTO(SolicitacaoRetirada solicitacaoRetirada){

        List<SolicitacaoItemRetirada> itensRetirada = solicitacaoItemRetiradaRepository
                .findBySolicitacaoRetiradaId(solicitacaoRetirada.getId());

        return new SolicitacaoRetiradaDetalhadaResponseDTO(
                solicitacaoRetirada.getId(),
                solicitacaoRetirada.getStatusSolicitacao(),
                solicitacaoRetirada.getJustificativa(),
                solicitacaoRetirada.getData(),
                itensRetirada
        );
    }



    private SolicitacaoRetiradaSimplificadaResponseDTO definirSimplesDTO(SolicitacaoRetirada solicitacaoRetirada){
        return new SolicitacaoRetiradaSimplificadaResponseDTO(
                solicitacaoRetirada.getId(),
                solicitacaoRetirada.getStatusSolicitacao(),
                solicitacaoRetirada.getJustificativa(),
                solicitacaoRetirada.getData(),
                solicitacaoRetirada.getUsuario().getNome()
        );
    }



    private SolicitacaoItemRetiradaResponseDTO definirItemDTO(SolicitacaoItemRetirada solicitacaoItemRetirada){
        return new SolicitacaoItemRetiradaResponseDTO(
                solicitacaoItemRetirada.getId(),
                solicitacaoItemRetirada.getProduto().getName(),
                solicitacaoItemRetirada.getQuantidade()
        );
    }



    private SolicitacaoRetirada findEntityByid(Long id){
        return solicitacaoRetiradaRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Solicitação não encontrada"
                ));
    }



    public SolicitacaoRetiradaSimplificadaResponseDTO save(SolicitacaoRetiradaRequestDTO dto){

        SolicitacaoRetirada solicitacaoRetirada = new SolicitacaoRetirada();

        Usuario usuario = usuarioRepository
                .findById(dto.usuario_id())
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Usuario não encontrado"));

        solicitacaoRetirada.setStatusSolicitacao(StatusSolicitacao.ABERTA);
        solicitacaoRetirada.setUsuario(usuario);
        solicitacaoRetirada.setData(LocalDateTime.now());
        solicitacaoRetirada.setJustificativa(dto.justificativa());

        solicitacaoRetirada = solicitacaoRetiradaRepository.save(solicitacaoRetirada);

        for(SolicitacaoItemRetiradaRequestDTO itemDTO : dto.listaItens()){

            SolicitacaoItemRetirada solicitacaoItemRetirada = new SolicitacaoItemRetirada();

            Produto produto = produtoRepository
                    .findById(itemDTO.produto_id())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Produto não encontrado"
                    ));

            solicitacaoItemRetirada.setSolicitacaoRetirada(solicitacaoRetirada);
            solicitacaoItemRetirada.setProduto(produto);
            solicitacaoItemRetirada.setQuantidade(itemDTO.quantidade());

            solicitacaoItemRetiradaRepository.save(solicitacaoItemRetirada);
        }

        return definirSimplesDTO(solicitacaoRetirada);
    }



    public String delete(Long id){

        SolicitacaoRetirada solicitacaoRetirada = findEntityByid(id);
        solicitacaoRetiradaRepository.delete(solicitacaoRetirada);

        return "Solicitação removida com sucesso";
    }



    public SolicitacaoItemRetiradaResponseDTO updateItem(SolicitacaoItemRetiradaUpdateRequestDTO dto){

        SolicitacaoItemRetirada solicitacaoItemRetirada = solicitacaoItemRetiradaRepository
                .findById(dto.SolicitacaoItemId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Item de retirada não encontrado"
                ));

        Produto produto = produtoRepository
                .findById(dto.produto_id())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                ));

        solicitacaoItemRetirada.setQuantidade(dto.quantidade());
        solicitacaoItemRetirada.setProduto(produto);

        solicitacaoItemRetiradaRepository.save(solicitacaoItemRetirada);

        return definirItemDTO(solicitacaoItemRetirada);
    }



    public SolicitacaoRetiradaDetalhadaResponseDTO findById(Long id){

        SolicitacaoRetirada solicitacaoRetirada = findEntityByid(id);

        return definirDTO(solicitacaoRetirada);
    }



    public List<SolicitacaoRetiradaSimplificadaResponseDTO> findAll() {
        return solicitacaoRetiradaRepository
                .findAll()
                .stream()
                .map(this::definirSimplesDTO)
                .toList();
    }



    public SolicitacaoRetiradaSimplificadaResponseDTO aprovar(Long id){

        SolicitacaoRetirada solicitacaoRetirada = findEntityByid(id);

        if(solicitacaoRetirada.getStatusSolicitacao() != StatusSolicitacao.ABERTA){
            throw new RuntimeException(
                    "Solicitação necessita estar com status de ABERTA"
            );
        }

        solicitacaoRetirada.setStatusSolicitacao(StatusSolicitacao.APROVADA);
        solicitacaoRetiradaRepository.save(solicitacaoRetirada);

        return definirSimplesDTO(solicitacaoRetirada);
    }



    public SolicitacaoRetiradaSimplificadaResponseDTO rejeitar(Long id){

        SolicitacaoRetirada solicitacaoRetirada = findEntityByid(id);

        if(solicitacaoRetirada.getStatusSolicitacao() != StatusSolicitacao.REJEITADA){
            throw new RuntimeException(
                    "Solicitação necessita estar com status de ABERTA"
            );
        }

        solicitacaoRetirada.setStatusSolicitacao(StatusSolicitacao.APROVADA);
        solicitacaoRetiradaRepository.save(solicitacaoRetirada);

        return definirSimplesDTO(solicitacaoRetirada);
    }
}
