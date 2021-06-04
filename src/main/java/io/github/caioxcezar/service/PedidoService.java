package io.github.caioxcezar.service;

import io.github.caioxcezar.domain.entity.Pedido;
import io.github.caioxcezar.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
