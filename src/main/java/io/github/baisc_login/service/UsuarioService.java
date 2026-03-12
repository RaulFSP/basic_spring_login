/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.baisc_login.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.baisc_login.dto.request.UsuarioCreateRequest;
import io.github.baisc_login.model.Role;
import io.github.baisc_login.model.Usuario;
import io.github.baisc_login.repository.RoleRepository;
import io.github.baisc_login.repository.UsuarioRepository;
import io.github.baisc_login.util.UsuarioBuilder;

/**
 *
 * @author raul
 */
@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUsuario(UsuarioCreateRequest request) {
        Role normalRole = new Role("NORMAL");
        if (roleRepository.existsByAuthority(normalRole.getAuthority())) {
            normalRole = roleRepository.findByAuthority("NORMAL").get();
        }
        Usuario usuario = UsuarioBuilder.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .role(normalRole)
                .build();
        usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado pelo username"));
    }

}
