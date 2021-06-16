package io.github.caioxcezar.rest.controller;

import io.github.caioxcezar.domain.entity.Usuario;
import io.github.caioxcezar.service.impl.UsuarioServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioServiceImp usuarioService;
    private final PasswordEncoder encoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@Valid @RequestBody Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioService.salvar(usuario);
    }
}
