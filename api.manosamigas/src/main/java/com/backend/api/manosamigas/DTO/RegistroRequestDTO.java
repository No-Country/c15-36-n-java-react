package com.backend.api.manosamigas.DTO;

import java.util.Date;

import com.backend.api.manosamigas.enums.UsuarioEnum;

public record RegistroRequestDTO(String login, String password, String nombre, Date fechaNacimiento, String direcion) {
	
}
