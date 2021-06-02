package io.github.caioxcezar.service;

import io.github.caioxcezar.domain.entity.Pedido;
import io.github.caioxcezar.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
