package io.github.caioxcezar.service.impl;

import io.github.caioxcezar.domain.entity.Cliente;
import io.github.caioxcezar.domain.entity.ItemPedido;
import io.github.caioxcezar.domain.entity.Pedido;
import io.github.caioxcezar.domain.entity.Produto;
import io.github.caioxcezar.domain.repository.Clientes;
import io.github.caioxcezar.domain.repository.Pedidos;
import io.github.caioxcezar.domain.repository.Produtos;
import io.github.caioxcezar.domain.repository.ItensPedido;
import io.github.caioxcezar.exception.RegraNegocioException;
import io.github.caioxcezar.rest.dto.ItemPedidoDTO;
import io.github.caioxcezar.rest.dto.PedidoDTO;
import io.github.caioxcezar.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    private final Pedidos pedidoRespository;
    private final Clientes clientesRespository;
    private final Produtos produtosRepository;
    private final ItensPedido itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Cliente cliente = clientesRespository.findById(dto.getCliente()).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItems());
        pedidoRespository.save(pedido);
        itensPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRespository.findByIdFetchItens(id);
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()) {
            throw new RegraNegocioException("Não é possivel realizar um pedido sem items");
        }

        return items.stream().map(dto -> {
            Produto produto = produtosRepository.findById(dto.getProduto()).orElseThrow(() -> new RegraNegocioException("Código do produto inválido: " + dto.getProduto()));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            itemPedido.setPedido(pedido);
            itemPedido.setQuantidade(dto.getQuantidade());

            return itemPedido;
        }).collect(Collectors.toList());
    }
}
