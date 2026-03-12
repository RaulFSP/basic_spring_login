/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package io.github.baisc_login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.baisc_login.model.Role;

/**
 *
 * @author raul
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByAuthority(String authority);

    Boolean existsByAuthority(String authority);
}
