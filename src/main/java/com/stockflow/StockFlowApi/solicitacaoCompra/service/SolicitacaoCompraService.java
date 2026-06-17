package com.stockflow.StockFlowApi.solicitacaoCompra.service;

import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.produto.repository.ProdutoRepository;
import com.stockflow.StockFlowApi.shared.enums.StatusSolicitacao;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.item.ItemSolicitacaoCompraCreateRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.item.ItemSolicitacaoCompraResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.item.ItemSolicitacaoCompraUpdateRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao.SolicitacaoCompraDetalhadaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao.SolicitacaoCompraRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao.SolicitacaoCompraSimplesResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.entity.ItemSolicitacaoCompra;
import com.stockflow.StockFlowApi.solicitacaoCompra.entity.SolicitacaoCompra;
import com.stockflow.StockFlowApi.solicitacaoCompra.mapper.SolicitacaoCompraMapper;
import com.stockflow.StockFlowApi.solicitacaoCompra.repository.ItemSolicitacaoCompraRepository;
import com.stockflow.StockFlowApi.solicitacaoCompra.repository.SolicitacaoCompraRepository;
import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import com.stockflow.StockFlowApi.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static com.stockflow.StockFlowApi.solicitacaoCompra.mapper.ItemSolicitacaoCompraMapper.toItemDTO;
import static com.stockflow.StockFlowApi.solicitacaoCompra.mapper.SolicitacaoCompraMapper.toDetalhadoDTO;
import static com.stockflow.StockFlowApi.solicitacaoCompra.mapper.SolicitacaoCompraMapper.toSimplesDTO;

@Data
@Service
@AllArgsConstructor
@Log4j2
@Transactional
public class SolicitacaoCompraService {

    private final SolicitacaoCompraRepository solicitacaoCompraRepository;
    private final ItemSolicitacaoCompraRepository itemSolicitacaoCompraRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;



    private SolicitacaoCompra findEntityById(Long id){
        return solicitacaoCompraRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Solicitação não encontrada"
                ));
    }



    public SolicitacaoCompraSimplesResponseDTO save(SolicitacaoCompraRequestDTO dto){

        Usuario usuario = usuarioRepository
                .findById(dto.usuario_id())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario não encontrado"
                ));

        SolicitacaoCompra solicitacaoCompra = new SolicitacaoCompra();

        solicitacaoCompra.setUsuario(usuario);
        solicitacaoCompra.setData(LocalDateTime.now());
        solicitacaoCompra.setStatusSolicitacao(StatusSolicitacao.ABERTA);
        solicitacaoCompra.setObservacao(dto.obs());

        solicitacaoCompraRepository.save(solicitacaoCompra);

        for(ItemSolicitacaoCompraCreateRequestDTO itemSolicitacaoDTO : dto.itensCompra()){

            ItemSolicitacaoCompra itemSolicitacaoCompra = new ItemSolicitacaoCompra();
            Produto produto = produtoRepository
                    .findById(itemSolicitacaoDTO.produto_id())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Produto não encontrado"
                    ));

            itemSolicitacaoCompra.setSolicitacaoCompra(solicitacaoCompra);
            itemSolicitacaoCompra.setQuantidadeSolicitada(itemSolicitacaoDTO.quantidadeSolicitada());
            itemSolicitacaoCompra.setObservacao(itemSolicitacaoDTO.observacao());
            itemSolicitacaoCompra.setProduto(produto);

            itemSolicitacaoCompraRepository.save(itemSolicitacaoCompra);

        }
        return toSimplesDTO(solicitacaoCompra);
    }



    public String delete(Long id){

        SolicitacaoCompra solicitacaoCompra = findEntityById(id);
        solicitacaoCompraRepository.delete(solicitacaoCompra);

        return "Solicitação removida com sucesso";
    }



    public ItemSolicitacaoCompraResponseDTO putItem(Long idItem, ItemSolicitacaoCompraUpdateRequestDTO dto){

        ItemSolicitacaoCompra itemSolicitacaoCompra = itemSolicitacaoCompraRepository
                .findById(idItem)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Item não encontrado"
                ));

        SolicitacaoCompra solicitacaoCompra = findEntityById(
                itemSolicitacaoCompra
                .getSolicitacaoCompra()
                .getId());

        if(solicitacaoCompra.getStatusSolicitacao() != StatusSolicitacao.ABERTA){
            throw new IllegalStateException(
                    "A solicitação só pode ser alterada enquanto estiver em ABERTO"
            );
        }

        if(dto.observacao() != null){
            itemSolicitacaoCompra.setObservacao(dto.observacao());
        }

        if(dto.produto_id() != null){
            Produto produto = produtoRepository
                    .findById(dto.produto_id())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "Produto não encontrado"
                            ));

            itemSolicitacaoCompra.setProduto(produto);
        }

        if(dto.quantidadeSolicitada() != null){
            itemSolicitacaoCompra.setQuantidadeSolicitada(dto.quantidadeSolicitada());
        }

        return toItemDTO(itemSolicitacaoCompraRepository.save(itemSolicitacaoCompra));

    }



    public List<SolicitacaoCompraSimplesResponseDTO> listAll(){

        return solicitacaoCompraRepository
                .findAll()
                .stream()
                .map(SolicitacaoCompraMapper::toSimplesDTO)
                .toList();
    }



    public SolicitacaoCompraDetalhadaResponseDTO findById(Long id){

        SolicitacaoCompra solicitacaoCompra = findEntityById(id);

        return toDetalhadoDTO(
                solicitacaoCompra,
                itemSolicitacaoCompraRepository.findBySolicitacaoCompraId(solicitacaoCompra.getId())
        );
    }



    public SolicitacaoCompraSimplesResponseDTO aprovar(Long id){

        SolicitacaoCompra solicitacaoCompra = findEntityById(id);

        if(solicitacaoCompra.getStatusSolicitacao() != StatusSolicitacao.ABERTA){
            throw new RuntimeException(
                    "Solicitação necessita estar com status de ABERTA"
            );
        }

        solicitacaoCompra.setStatusSolicitacao(StatusSolicitacao.APROVADA);
        solicitacaoCompraRepository.save(solicitacaoCompra);

        return toSimplesDTO(solicitacaoCompra);
    }



    public SolicitacaoCompraSimplesResponseDTO rejeitar(Long id){

        SolicitacaoCompra solicitacaoCompra = findEntityById(id);

        if(solicitacaoCompra.getStatusSolicitacao() != StatusSolicitacao.ABERTA){
            throw new RuntimeException(
                    "Solicitação necessita estar com status de ABERTA"
            );
        }

        solicitacaoCompra.setStatusSolicitacao(StatusSolicitacao.REJEITADA);
        solicitacaoCompraRepository.save(solicitacaoCompra);

        return toSimplesDTO(solicitacaoCompra);
    }



    public SolicitacaoCompraSimplesResponseDTO comprar(Long id){

        SolicitacaoCompra solicitacaoCompra = findEntityById(id);

        if(solicitacaoCompra.getStatusSolicitacao() != StatusSolicitacao.APROVADA){
            throw new RuntimeException(
                    "Solicitação necessita estar com status de APROVADA"
            );
        }

        solicitacaoCompra.setStatusSolicitacao(StatusSolicitacao.COMPRADA);
        solicitacaoCompraRepository.save(solicitacaoCompra);

        return toSimplesDTO(solicitacaoCompra);
    }
}
