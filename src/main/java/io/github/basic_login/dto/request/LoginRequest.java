/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package io.github.basic_login.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author raul
 */
public record LoginRequest(
        @NotBlank
        String username,
        @NotBlank
        String password) {

}
