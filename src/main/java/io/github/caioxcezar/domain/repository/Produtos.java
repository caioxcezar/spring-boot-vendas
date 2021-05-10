package io.github.caioxcezar.domain.repository;

import io.github.caioxcezar.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
