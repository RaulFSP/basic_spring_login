/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.basic_login.filter;

import java.io.IOException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.basic_login.model.Usuario;
import io.github.basic_login.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author raul
 */
@Component
public class LoginFilter extends OncePerRequestFilter {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginFilter(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/login") && request.getMethod().equals("POST")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (!usuarioRepository.existsByUsername(username)) {
                String erroUsuario = "{\"erro\":\"O usuário não foi encontrado\"}";
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write(erroUsuario);
                return;
            }
            Usuario usuario = usuarioRepository.findByUsername(username).get();
            if (!passwordEncoder.matches(password, usuario.getPassword())) {
                String erroUsuario = "{\"erro\":\"A senha está incorreta\"}";
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write(erroUsuario);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}
