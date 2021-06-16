package io.github.caioxcezar.service.impl;

import io.github.caioxcezar.domain.entity.Usuario;
import io.github.caioxcezar.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImp implements UserDetailsService {

    private final PasswordEncoder encoder;
    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username)
                .map(usuario -> {
                    String[] roles = usuario.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};
                    return User
                            .builder()
                            .username(usuario.getLogin())
                            .password(usuario.getSenha())
                            .roles(roles)
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }
}
