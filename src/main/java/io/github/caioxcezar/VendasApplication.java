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

    @Bean
    CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            System.out.println("Salvando Clientes");
            clientes.save(new Cliente("Caio"));
            clientes.save(new Cliente("Outro Cliente"));

            boolean existe = clientes.existsByNome("Caio");
            System.out.println("Exite um Cliente com o nome Caio? " + existe);


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class);
    }
}
