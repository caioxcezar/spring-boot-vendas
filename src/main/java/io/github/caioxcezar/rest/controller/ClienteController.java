package io.github.caioxcezar.rest.controller;

import io.github.caioxcezar.domain.entity.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClienteController {

    @RequestMapping(
            value = "/api/clientes/hello/{nome}",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"}
    )
    @ResponseBody
    public Cliente helloCliente(@PathVariable("nome") String nomeCliente, @RequestBody Cliente cliente) {
        cliente.setNome(nomeCliente);
        return cliente;
    }
}
