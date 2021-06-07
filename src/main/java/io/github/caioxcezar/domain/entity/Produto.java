package io.github.caioxcezar.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descricao")
    @NotEmpty(message = "Campo descrição obrigatório.")
    private String descricao;
    @Column(name = "preco_unitario")
    @NotNull(message = "Campo preço é obrigatorio.")
    private BigDecimal preco;
}
