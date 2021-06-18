package io.github.caioxcezar.rest.controller;

import io.github.caioxcezar.domain.entity.Usuario;
import io.github.caioxcezar.exception.SenhaInvalidaException;
import io.github.caioxcezar.rest.dto.CredenciaisDTO;
import io.github.caioxcezar.rest.dto.TokenDTO;
import io.github.caioxcezar.security.jwt.JwtService;
import io.github.caioxcezar.service.impl.UsuarioServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioServiceImp usuarioService;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@Valid @RequestBody Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioService.salvar(usuario);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
        try {
            Usuario usuario = Usuario
                    .builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha())
                    .build();
            usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
