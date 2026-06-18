package com.stockflow.StockFlowApi.solicitacaoRetirada.service;

import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.produto.repository.ProdutoRepository;
import com.stockflow.StockFlowApi.shared.enums.StatusSolicitacao;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item.ItemSolicitacaoRetiradaCreateRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item.ItemSolicitacaoRetiradaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.item.ItemSolicitacaoRetiradaUpdateDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao.SolicitacaoRetiradaRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao.SolicitacaoRetiradaDetalhadaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.dto.solicitacao.SolicitacaoRetiradaSimplificadaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoRetirada.entity.ItemSolicitacaoRetirada;
import com.stockflow.StockFlowApi.solicitacaoRetirada.entity.SolicitacaoRetirada;
import com.stockflow.StockFlowApi.solicitacaoRetirada.mapper.SolicitacaoRetiradaMapper;
import com.stockflow.StockFlowApi.solicitacaoRetirada.repository.ItemSolicitacaoRetiradaRepository;
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

import static com.stockflow.StockFlowApi.solicitacaoRetirada.mapper.ItemSolicitacaoRetiradaMapper.toItemDTO;
import static com.stockflow.StockFlowApi.solicitacaoRetirada.mapper.SolicitacaoRetiradaMapper.toDetalhadaRetiradaDTO;
import static com.stockflow.StockFlowApi.solicitacaoRetirada.mapper.SolicitacaoRetiradaMapper.toSimplesRetiradaDTO;

@Service
@AllArgsConstructor
@Data
@Transactional
public class SolicitacaoRetiradaService {

    private final SolicitacaoRetiradaRepository solicitacaoRetiradaRepository;
    private final ItemSolicitacaoRetiradaRepository itemSolicitacaoRetiradaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;



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
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Usuario não encontrado"));

        solicitacaoRetirada.setStatusSolicitacao(StatusSolicitacao.ABERTA);
        solicitacaoRetirada.setUsuario(usuario);
        solicitacaoRetirada.setData(LocalDateTime.now());
        solicitacaoRetirada.setJustificativa(dto.justificativa());

        solicitacaoRetirada = solicitacaoRetiradaRepository.save(solicitacaoRetirada);

        for(ItemSolicitacaoRetiradaCreateRequestDTO itemDTO : dto.listaItens()){

            ItemSolicitacaoRetirada itemSolicitacaoRetirada = new ItemSolicitacaoRetirada();

            Produto produto = produtoRepository
                    .findById(itemDTO.produto_id())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Produto não encontrado"
                    ));

            itemSolicitacaoRetirada.setSolicitacaoRetirada(solicitacaoRetirada);
            itemSolicitacaoRetirada.setProduto(produto);
            itemSolicitacaoRetirada.setQuantidade(itemDTO.quantidade());

            itemSolicitacaoRetiradaRepository.save(itemSolicitacaoRetirada);
        }

        return toSimplesRetiradaDTO(solicitacaoRetirada);
    }



    public String delete(Long id){

        SolicitacaoRetirada solicitacaoRetirada = findEntityByid(id);
        solicitacaoRetiradaRepository.delete(solicitacaoRetirada);

        return "Solicitação removida com sucesso";
    }



    public ItemSolicitacaoRetiradaResponseDTO updateItem(Long idItem, ItemSolicitacaoRetiradaUpdateDTO dto){

        ItemSolicitacaoRetirada itemSolicitacaoRetirada = itemSolicitacaoRetiradaRepository
                .findById(idItem)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Item de retirada não encontrado"
                ));

        SolicitacaoRetiradaDetalhadaResponseDTO retiradaResponseDTO = findById(
                itemSolicitacaoRetirada
                .getSolicitacaoRetirada()
                .getId()
        );

        if(retiradaResponseDTO.statusSolicitacao() != StatusSolicitacao.ABERTA){
            throw new IllegalStateException(
                    "A solicitação só pode ser alterada enquanto estiver em ABERTO"
            );
        }

        if(dto.produto_id() != null){
            Produto produto = produtoRepository
                    .findById(dto.produto_id())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Produto não encontrado"
                    ));

            itemSolicitacaoRetirada.setProduto(produto);
        }

        if(dto.quantidade() != null){
            itemSolicitacaoRetirada.setQuantidade(dto.quantidade());
        }

        itemSolicitacaoRetiradaRepository.save(itemSolicitacaoRetirada);

        return toItemDTO(itemSolicitacaoRetirada);
    }



    public SolicitacaoRetiradaDetalhadaResponseDTO findById(Long id){

        return toDetalhadaRetiradaDTO(
                findEntityByid(id),
                itemSolicitacaoRetiradaRepository.findBySolicitacaoRetiradaId(id)
                );
    }



    public List<SolicitacaoRetiradaSimplificadaResponseDTO> findAll() {
        return solicitacaoRetiradaRepository
                .findAll()
                .stream()
                .map(SolicitacaoRetiradaMapper::toSimplesRetiradaDTO)
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

        return toSimplesRetiradaDTO(solicitacaoRetirada);
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

        return toSimplesRetiradaDTO(solicitacaoRetirada);
    }
}
