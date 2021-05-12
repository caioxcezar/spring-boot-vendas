package io.github.caioxcezar;

import io.github.caioxcezar.domain.entity.Cliente;
import io.github.caioxcezar.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired Clientes clientes) {
        return args -> {
            clientes.save(new Cliente("Caio"));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class);
    }
}
