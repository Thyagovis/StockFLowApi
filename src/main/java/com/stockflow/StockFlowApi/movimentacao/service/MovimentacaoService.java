package com.stockflow.StockFlowApi.movimentacao.service;

import com.stockflow.StockFlowApi.estoque.dto.EstoqueMovementDTO;
import com.stockflow.StockFlowApi.estoque.service.EstoqueService;
import com.stockflow.StockFlowApi.movimentacao.dto.MovimentacaoLoteRequestDTO;
import com.stockflow.StockFlowApi.movimentacao.dto.MovimentacaoLoteResponseDTO;
import com.stockflow.StockFlowApi.movimentacao.dto.MovimentacaoMapper;
import com.stockflow.StockFlowApi.movimentacao.dto.MovimentacaoLoteSummaryDTO;
import com.stockflow.StockFlowApi.movimentacao.entity.ItemMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.entity.MovimentacaoLote;
import com.stockflow.StockFlowApi.movimentacao.repository.MovimentacaoLoteRepository;
import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.produto.repository.ProdutoRepository;
import com.stockflow.StockFlowApi.shared.exceptions.NotFoundException;
import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
@Transactional
public class MovimentacaoService {

    private final MovimentacaoLoteRepository movimentacaoLoteRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueService estoqueService;

    @Transactional
    public MovimentacaoLoteResponseDTO save(MovimentacaoLoteRequestDTO dto) {

        Usuario usuario = pegarUsuarioDaAutenticacao();

        var movimentacao = new MovimentacaoLote(
                dto.tipoMovimentacao(),
                dto.origemMovimentacao(),
                dto.observacao().trim(),
                usuario
        );


        dto.itens().forEach(itemDTO -> {
            Produto produto = produtoRepository.findById(itemDTO.produtoId())
                    .orElseThrow(() -> new NotFoundException("Produto não encontrado"));


            ItemMovimentacao item = new ItemMovimentacao(
                    itemDTO.quantidade(),
                    itemDTO.custoUnitario(),
                    itemDTO.custoUnitario().multiply(
                            BigDecimal.valueOf(itemDTO.quantidade())
                    ),
                    produto,
                    movimentacao
            );

            estoqueService.movimentarEstoque(new EstoqueMovementDTO(
                    itemDTO.produtoId(),
                    itemDTO.quantidade(),
                    movimentacao.getTipoMovimentacao()
            ));

            movimentacao.adicionarItem(item);
        });

        return MovimentacaoMapper.toMovimentacaoResponseDTO(movimentacaoLoteRepository.save(movimentacao));
    }

    public List<MovimentacaoLoteSummaryDTO> listAllSummary() {
        return movimentacaoLoteRepository.findAll()
                .stream().map(MovimentacaoMapper::toMovimentacaoLoteSummaryDTO).toList();
    }

    public List<MovimentacaoLoteResponseDTO> listAllFull() {
        return movimentacaoLoteRepository.findAll()
                .stream().map(MovimentacaoMapper::toMovimentacaoResponseDTO).toList();
    }

    public MovimentacaoLoteResponseDTO findById(Long id) {
        var movimentacao = movimentacaoLoteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movimentação Lote não encontrada"));

        return MovimentacaoMapper.toMovimentacaoResponseDTO(movimentacao);
    }

    private Usuario pegarUsuarioDaAutenticacao() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            throw new NotFoundException("Usuário não encontrado");
        }

        if (auth.getPrincipal() instanceof Usuario usuario) {
            return usuario;
        }

        throw new NotFoundException("Usuário não encontrado");
    }

}