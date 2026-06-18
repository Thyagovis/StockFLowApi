package com.stockflow.StockFlowApi.movimentacao.service;

import com.stockflow.StockFlowApi.movimentacao.dto.ItemMovimentacaoDTO;
import com.stockflow.StockFlowApi.movimentacao.dto.MovimentacaoLoteRequestDTO;
import com.stockflow.StockFlowApi.movimentacao.dto.MovimentacaoLoteResponseDTO;
import com.stockflow.StockFlowApi.movimentacao.entity.ItemMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.entity.MovimentacaoLote;
import com.stockflow.StockFlowApi.movimentacao.repository.ItemMovimentacaoRepository;
import com.stockflow.StockFlowApi.movimentacao.repository.MovimentacaoLoteRepository;
import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.produto.repository.ProdutoRepository;
import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import com.stockflow.StockFlowApi.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
@Transactional
public class MovimentacaoService {

    private final MovimentacaoLoteRepository movimentacaoLoteRepository;

    private final ItemMovimentacaoRepository itemMovimentacaoRepository;

    private final ProdutoRepository produtoRepository;

    private final UsuarioRepository usuarioRepository;

    private MovimentacaoLoteResponseDTO definirDTO(
            MovimentacaoLote movimentacao) {

        List<ItemMovimentacaoDTO> itens =
                itemMovimentacaoRepository
                        .findByMovimentacaoLoteId(movimentacao.getId())
                        .stream()
                        .map(item -> new ItemMovimentacaoDTO(
                                item.getProduto().getId(),
                                item.getQuantidade(),
                                item.getCustoUnitario()
                        ))
                        .toList();

        return new MovimentacaoLoteResponseDTO(
                movimentacao.getId(),
                movimentacao.getTipoMovimentacao(),
                movimentacao.getOrigemMovimentacao(),
                movimentacao.getData(),
                movimentacao.getObservacao(),
                movimentacao.getUsuario().getId(),
                itens
        );
    }

    private MovimentacaoLote findEntityById(Long id) {

        return movimentacaoLoteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Movimentação não encontrada"
                ));
    }

    public MovimentacaoLote save(MovimentacaoLoteRequestDTO dto) {

        MovimentacaoLote movimentacao = new MovimentacaoLote();
        Usuario usuario = usuarioRepository
                .findById(dto.criadoPorId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario não encontrado"
                ));

        movimentacao.setTipoMovimentacao(dto.tipoMovimentacao());
        movimentacao.setOrigemMovimentacao(dto.origemMovimentacao());
        movimentacao.setObservacao(dto.observacao());
        movimentacao.setUsuario(usuario);
        movimentacao.setData(LocalDateTime.now());

        movimentacao = movimentacaoLoteRepository.save(movimentacao);

        if (dto.itens() != null && !dto.itens().isEmpty()) {

            for (ItemMovimentacaoDTO itemDTO : dto.itens()) {

                Produto produto = produtoRepository
                        .findById(itemDTO.produtoId())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Produto não encontrado"
                        ));

                ItemMovimentacao item = new ItemMovimentacao();

                item.setMovimentacaoLote(movimentacao);
                item.setProduto(produto);
                item.setQuantidade(itemDTO.quantidade());
                item.setCustoUnitario(itemDTO.custoUnitario());

                item.setCustoTotal(
                        itemDTO.quantidade()
                                .multiply(itemDTO.custoUnitario())
                );

                itemMovimentacaoRepository.save(item);
            }
        }

        return movimentacao;
    }

    public List<MovimentacaoLoteResponseDTO> listAll() {

        return movimentacaoLoteRepository
                .findAll()
                .stream()
                .map(this::definirDTO)
                .toList();
    }

    public MovimentacaoLoteResponseDTO findById(Long id) {

        return definirDTO(findEntityById(id));
    }

}