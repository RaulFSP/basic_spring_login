/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.basic_login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.basic_login.dto.request.UsuarioCreateRequest;
import io.github.basic_login.service.UsuarioService;

/**
 *
 * @author raul
 */
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Void> createUsuario(@RequestBody UsuarioCreateRequest request) {
        usuarioService.createUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/home")
    @PreAuthorize("hasRole('NORMAL')")
    public ResponseEntity<String> usuarioNormalPage() {
        return ResponseEntity.ok("Página de usuário");
    }

}
