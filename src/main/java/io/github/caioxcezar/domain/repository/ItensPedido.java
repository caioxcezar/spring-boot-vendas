package io.github.caioxcezar.domain.repository;

import io.github.caioxcezar.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {
}
