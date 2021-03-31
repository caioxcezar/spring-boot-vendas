package io.github.caioxcezar.service;

import io.github.caioxcezar.model.Cliente;
import io.github.caioxcezar.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    private ClientesRepository clientesRepository;

    @Autowired //Pode ser omitido em construtores
    public ClientesService(ClientesRepository repository) {
        clientesRepository = repository;
    }

    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        this.clientesRepository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente) {
        //aplicar validações
    }
}
