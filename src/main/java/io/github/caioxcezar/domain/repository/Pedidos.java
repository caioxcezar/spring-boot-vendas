package io.github.caioxcezar.domain.repository;

import io.github.caioxcezar.domain.entity.Cliente;
import io.github.caioxcezar.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
    Set<Pedido> findByCliente(Cliente cliente);
}
