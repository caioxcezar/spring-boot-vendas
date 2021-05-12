package io.github.caioxcezar.rest.controller;

import io.github.caioxcezar.domain.entity.Cliente;
import io.github.caioxcezar.domain.repository.Clientes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClienteController {

    private final Clientes clientes;

    public ClienteController(Clientes clientes){
        this.clientes = clientes;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);
        if (cliente.isPresent())
            return ResponseEntity.ok(cliente.get());
        else
            return ResponseEntity.notFound().build();
    }
}
