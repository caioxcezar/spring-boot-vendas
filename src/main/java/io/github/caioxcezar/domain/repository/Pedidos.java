package io.github.caioxcezar.domain.repository;

import io.github.caioxcezar.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
