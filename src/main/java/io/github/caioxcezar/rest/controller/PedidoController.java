package io.github.caioxcezar.rest.controller;

import io.github.caioxcezar.domain.entity.Pedido;
import io.github.caioxcezar.domain.repository.Pedidos;
import io.github.caioxcezar.service.PedidoService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }
}
