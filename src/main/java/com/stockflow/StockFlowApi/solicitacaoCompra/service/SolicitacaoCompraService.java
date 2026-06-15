package com.stockflow.StockFlowApi.solicitacaoCompra.service;

import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.produto.repository.ProdutoRepository;
import com.stockflow.StockFlowApi.shared.enums.StatusSolicitacao;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.item.ItemSolicitacaoCompraRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.item.ItemSolicitacaoCompraResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao.SolicitacaoCompraDetalhadaResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao.SolicitacaoCompraRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao.SolicitacaoCompraSimplesResponseDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.entity.ItemSolicitacaoCompra;
import com.stockflow.StockFlowApi.solicitacaoCompra.entity.SolicitacaoCompra;
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



    private SolicitacaoCompraSimplesResponseDTO definirSimplesDTO(SolicitacaoCompra solicitacaoCompra){
        return new SolicitacaoCompraSimplesResponseDTO(
                solicitacaoCompra.getId(),
                solicitacaoCompra.getStatusSolicitacao(),
                solicitacaoCompra.getObservacao(),
                solicitacaoCompra.getData()
        );
    }



    private SolicitacaoCompraDetalhadaResponseDTO definirDetalhadoDTO(SolicitacaoCompra solicitacaoCompra){

        List<ItemSolicitacaoCompraResponseDTO> listaItens = itemSolicitacaoCompraRepository
                .findBySolicitacaoCompraId(solicitacaoCompra.getId())
                .stream()
                .map(this::definirItemDTO)
                .toList();

        return new SolicitacaoCompraDetalhadaResponseDTO(
                solicitacaoCompra.getId(),
                solicitacaoCompra.getStatusSolicitacao(),
                solicitacaoCompra.getObservacao(),
                solicitacaoCompra.getData(),
                listaItens
        );
    }



    private ItemSolicitacaoCompraResponseDTO definirItemDTO(ItemSolicitacaoCompra itemSolicitacaoCompra){
        return new ItemSolicitacaoCompraResponseDTO(
                itemSolicitacaoCompra.getId(),
                itemSolicitacaoCompra.getProduto().getName(),
                itemSolicitacaoCompra.getQuantidadeSolicitada()
        );
    }



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

        for(ItemSolicitacaoCompraRequestDTO itemSolicitacaoDTO : dto.itensCompra()){

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
        return definirSimplesDTO(solicitacaoCompra);
    }



    public String delete(Long id){

        SolicitacaoCompra solicitacaoCompra = findEntityById(id);
        solicitacaoCompraRepository.delete(solicitacaoCompra);

        return "Solicitação removida com sucesso";
    }



    public ItemSolicitacaoCompraResponseDTO putItem(Long idItem, ItemSolicitacaoCompraRequestDTO dto){

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

        return definirItemDTO(itemSolicitacaoCompraRepository.save(itemSolicitacaoCompra));

    }



    public List<SolicitacaoCompraSimplesResponseDTO> listAll(){

        return solicitacaoCompraRepository
                .findAll()
                .stream()
                .map(this::definirSimplesDTO)
                .toList();
    }



    public SolicitacaoCompraDetalhadaResponseDTO findById(Long id){

        SolicitacaoCompra solicitacaoCompra = findEntityById(id);

        return definirDetalhadoDTO(solicitacaoCompra);
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

        return definirSimplesDTO(solicitacaoCompra);
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

        return definirSimplesDTO(solicitacaoCompra);
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

        return definirSimplesDTO(solicitacaoCompra);
    }
}
