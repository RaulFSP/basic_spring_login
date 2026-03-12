/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.baisc_login.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.baisc_login.model.Usuario;
import io.github.baisc_login.repository.UsuarioRepository;
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
            Map<String, String> msg = verificaUsuario(username, password);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg.get("erro"));
            return;
        }
        filterChain.doFilter(request, response);
    }

    private Map<String, String> verificaUsuario(String username, String password) {
        Map<String, String> erro = new HashMap<>();
        if (usuarioRepository.existsByUsername(username)) {
            Usuario usuario = usuarioRepository.findByUsername(username).get();
            if (!usuario.getPassword().equals(passwordEncoder.encode(password))) {
                erro.put("erro", "A senha está incorreta");
            }

        } else {
            erro.put("erro", "O usuário não existe");
        }
        return erro;

    }
}
