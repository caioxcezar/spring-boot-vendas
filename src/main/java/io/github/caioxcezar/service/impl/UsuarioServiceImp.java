package io.github.caioxcezar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp implements UserDetailsService {

    private final PasswordEncoder encoder;

    public UsuarioServiceImp(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!username.equals("caio")) throw new UsernameNotFoundException("Usuário não encontrado.");
        return User
                .builder()
                .username("caio")
                .password(encoder.encode("123"))
                .roles("USER", "ADMIN")
                .build();
    }
}
