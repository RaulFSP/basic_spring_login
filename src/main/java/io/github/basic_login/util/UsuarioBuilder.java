/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.basic_login.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import io.github.basic_login.model.Role;
import io.github.basic_login.model.Usuario;

/**
 *
 * @author raul
 */
@Component
public class UsuarioBuilder {

    private Long id;

    private String username;

    private String password;

    private String email;

    private final Set<Role> roles = new HashSet<>();

    private UsuarioBuilder() {
    }

    public static UsuarioBuilder builder() {
        return new UsuarioBuilder();
    }

    public UsuarioBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UsuarioBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UsuarioBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder roles(Collection<Role> roles) {
        this.roles.addAll(roles);
        return this;
    }

    public UsuarioBuilder role(Role role) {
        this.roles.add(role);
        return this;
    }

    public Usuario build() {
        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setUsername(this.username);
        usuario.setPassword(this.password);
        usuario.setEmail(this.email);
        usuario.getRoles().addAll(this.roles);
        return usuario;
    }

}
