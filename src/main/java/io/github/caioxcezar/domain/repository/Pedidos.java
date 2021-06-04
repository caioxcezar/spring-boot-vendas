package io.github.caioxcezar.domain.repository;

import io.github.caioxcezar.domain.entity.Cliente;
import io.github.caioxcezar.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
    Set<Pedido> findByCliente(Cliente cliente);

    @Query("select p from Pedido p left join fetch p.itens where p.id = :id")
    Optional<Pedido> findByIdFetchItens(Integer id);
}
