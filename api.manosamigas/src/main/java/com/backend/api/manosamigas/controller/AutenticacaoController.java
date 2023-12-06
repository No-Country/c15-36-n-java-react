package com.backend.api.manosamigas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.api.manosamigas.DTO.LoginRequestDTO;
import com.backend.api.manosamigas.DTO.RegistroRequestDTO;
import com.backend.api.manosamigas.entity.Usuario;
import com.backend.api.manosamigas.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@PostMapping("/login")
	public ResponseEntity autenticarUsuario(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
		var loginPassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.login(), loginRequestDTO.password());
		try {
			var auth = this.authenticationManager.authenticate(loginPassword);
			System.out.println(auth);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
		}
		
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/registrar")
	public ResponseEntity registrarUsuario(@RequestBody @Valid RegistroRequestDTO registroRequestDTO){
		if(this.usuarioRepository.findByEmail(registroRequestDTO.login()) != null) {
	        return ResponseEntity.badRequest().build();
	    }
		
		BCryptPasswordEncoder  passwordEncoder = new BCryptPasswordEncoder();
		
		String encryptedPassword = passwordEncoder.encode(registroRequestDTO.password());
		
		Usuario newUser = new Usuario(registroRequestDTO.login(), encryptedPassword,
				registroRequestDTO.nombre(), registroRequestDTO.fechaNacimiento(), registroRequestDTO.direcion());
		
		this.usuarioRepository.save(newUser);
		return ResponseEntity.ok().build();
	}
	


}
