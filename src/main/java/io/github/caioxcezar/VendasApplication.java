package io.github.caioxcezar;

import io.github.caioxcezar.domain.entity.Cliente;
import io.github.caioxcezar.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            System.out.println("Salvando Clientes");
            clientes.save(new Cliente("Caio"));
            clientes.save(new Cliente("Outro Cliente"));

            List<Cliente> c = clientes.encontrarPorNome("Caio");
            c.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class);
    }
}
