/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package io.github.basic_login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.basic_login.model.Usuario;

/**
 *
 * @author raul
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    Boolean existsByUsername(String username);
}
