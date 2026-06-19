package com.stockflow.StockFlowApi.chamado.service;

import com.stockflow.StockFlowApi.chamado.dto.ChamadoRequestDTO;
import com.stockflow.StockFlowApi.chamado.dto.ChamadoResponseDTO;
import com.stockflow.StockFlowApi.chamado.dto.ItemChamadoDTO;
import com.stockflow.StockFlowApi.chamado.entity.Chamado;
import com.stockflow.StockFlowApi.chamado.entity.ItemChamado;
import com.stockflow.StockFlowApi.chamado.enums.StatusChamado;
import com.stockflow.StockFlowApi.chamado.repository.ChamadoRepository;
import com.stockflow.StockFlowApi.chamado.repository.ItemChamadoRepository;
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
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;

    private final ItemChamadoRepository itemChamadoRepository;

    private final ProdutoRepository produtoRepository;

    private final UsuarioRepository usuarioRepository;

    private ChamadoResponseDTO definirDTO(Chamado chamado) {

        List<ItemChamadoDTO> itens = itemChamadoRepository
                .findByChamadoId(chamado.getId())
                .stream()
                .map(item -> new ItemChamadoDTO(
                        item.getProduto().getId(),
                        item.getQuantidade(),
                        item.getObservacao()
                ))
                .toList();

        return new ChamadoResponseDTO(
                chamado.getId(),
                chamado.getUsuario().getId(),
                chamado.getTipoChamado(),
                chamado.getStatusChamado(),
                chamado.getDescricao(),
                chamado.getDataAbertura(),
                chamado.getDataFechamento(),
                itens
        );
    }

    private Chamado findEntityById(Long id) {

        return chamadoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Chamado não encontrado."
                ));
    }

    public ChamadoResponseDTO save(ChamadoRequestDTO dto) {

        log.info("Iniciando criação de chamado.");

        if (dto.itens() == null || dto.itens().isEmpty()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O chamado deve possuir pelo menos um item."
            );

        }

        Usuario usuario = usuarioRepository
                .findById(dto.usuarioId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado."
                ));

        Chamado chamado = new Chamado();

        chamado.setUsuario(usuario);
        chamado.setTipoChamado(dto.tipoChamado());
        chamado.setStatusChamado(StatusChamado.ABERTO);
        chamado.setDescricao(dto.descricao());
        chamado.setDataAbertura(LocalDateTime.now());

        chamado = chamadoRepository.save(chamado);

        for (ItemChamadoDTO itemDTO : dto.itens()) {

            if (itemDTO.quantidade().signum() <= 0) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "A quantidade deve ser maior que zero."
                );
            }

            Produto produto = produtoRepository
                    .findById(itemDTO.produtoId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Produto não encontrado."
                    ));

            ItemChamado item = new ItemChamado();

            item.setChamado(chamado);
            item.setProduto(produto);
            item.setQuantidade(itemDTO.quantidade());
            item.setObservacao(itemDTO.observacao());

            itemChamadoRepository.save(item);
        }

        log.info("Chamado {} criado com sucesso.", chamado.getId());

        return definirDTO(chamado);
    }

    public List<ChamadoResponseDTO> listAll() {

        log.info("Listando todos os chamados.");

        return chamadoRepository
                .findAll()
                .stream()
                .map(this::definirDTO)
                .toList();
    }

    public ChamadoResponseDTO findById(Long id) {

        log.info("Buscando chamado de id {}.", id);

        return definirDTO(findEntityById(id));
    }

}