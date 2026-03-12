/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.basic_login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.github.basic_login.filter.LoginFilter;
import io.github.basic_login.service.UsuarioService;

/**
 *
 * @author raul
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UsuarioService usuarioService;
    private final LoginFilter loginFilter;

    public SecurityConfig(UsuarioService usuarioService, LoginFilter loginFilter) {
        this.usuarioService = usuarioService;
        this.loginFilter = loginFilter;
    }

    @Bean
    SecurityFilterChain sfc(HttpSecurity hs) throws Exception {
        return hs
                .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/login",
                            "/h2/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/usuarios").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> {
                    session.maximumSessions(1);
                })
                .formLogin(form -> {
                    form.loginProcessingUrl("/login").permitAll();
                    form.defaultSuccessUrl("/usuarios/home", true);
                    form.failureUrl("/login?error=true");

                })
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout"))
                .userDetailsService(usuarioService)
                .addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration ac) throws Exception {
        return ac.getAuthenticationManager();
    }
}
