package io.github.caioxcezar.config;

import io.github.caioxcezar.service.impl.UsuarioServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsuarioServiceImp usuarioService;

    public SecurityConfig(@Lazy UsuarioServiceImp usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/clientes/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/api/produtos/**").hasAnyRole("ADMIN")
                .antMatchers("/api/pedidos/**").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
