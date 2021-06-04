package io.github.caioxcezar.rest.controller;

import io.github.caioxcezar.domain.entity.ItemPedido;
import io.github.caioxcezar.domain.entity.Pedido;
import io.github.caioxcezar.domain.enums.StatusPedido;
import io.github.caioxcezar.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.caioxcezar.rest.dto.InformacoesItemPedidoDTO;
import io.github.caioxcezar.rest.dto.InformacoesPedidoDTO;
import io.github.caioxcezar.rest.dto.PedidoDTO;
import io.github.caioxcezar.service.PedidoService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return service.obterPedidoCompleto(id).map(pedido -> converter(pedido)).orElseThrow(()->new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(converter(pedido.getItens()))
                .build();
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto) {
        service.atualizaStatus(id, StatusPedido.valueOf(dto.getNovoStatus()));
    }

    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) return Collections.emptyList();

        return itens.stream().map(item -> InformacoesItemPedidoDTO.builder()
                .descricaoProduto(item.getProduto().getDescricao())
                .quantidade(item.getQuantidade())
                .precoUnitario(item.getProduto().getPreco())
                .build()
        ).collect(Collectors.toList());
    }
}
