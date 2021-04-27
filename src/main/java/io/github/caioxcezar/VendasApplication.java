package io.github.caioxcezar;

import io.github.caioxcezar.domain.entity.Cliente;
import io.github.caioxcezar.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            clientes.salvar(new Cliente("Caio"));
            clientes.salvar(new Cliente("Outro Cliente"));

            List<Cliente> todosClientes = clientes.obterTodos();

            todosClientes.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class);
    }
}
