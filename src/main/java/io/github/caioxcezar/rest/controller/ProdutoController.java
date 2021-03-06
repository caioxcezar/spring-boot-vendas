package io.github.caioxcezar.rest.controller;

import io.github.caioxcezar.domain.entity.Produto;
import io.github.caioxcezar.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final Produtos repository;

    public ProdutoController(Produtos repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto create(@RequestBody @Valid Produto produto){
        return repository.save(produto);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Produto read(@PathVariable Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<Produto> read(Produto produto){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(produto, matcher);
        return repository.findAll(example);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Produto produto){
        repository.findById(id).map(prd -> {
            produto.setId(prd.getId());
            repository.save(produto);
            return produto;
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.findById(id).map(prd -> {
            repository.delete(prd);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
    }
}
