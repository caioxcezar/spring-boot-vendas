package io.github.caioxcezar.service.impl;

import io.github.caioxcezar.domain.repository.Pedidos;
import io.github.caioxcezar.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final Pedidos respository;

    public PedidoServiceImpl(Pedidos respository) {
        this.respository = respository;
    }
}
