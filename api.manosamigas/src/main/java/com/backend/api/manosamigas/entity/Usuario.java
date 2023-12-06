package com.backend.api.manosamigas.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend.api.manosamigas.enums.UsuarioEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String email;
	private String contrasena;
	private Date fechaNacimiento;
	private String direcion;
	private UsuarioEnum rol;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.rol ==  UsuarioEnum.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
		else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return contrasena;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public Usuario(String email, String contrasena, UsuarioEnum rol) {
		this.email = email;
		this.contrasena = contrasena;
		this.rol = rol;
	}
	
	public Usuario(String email, String contrasena, UsuarioEnum rol, String nombre, Date fechaNacimiento, String direcion) {
	    this.email = email;
	    this.contrasena = contrasena;
	    this.rol = rol;
	    this.nombre = nombre;
	    this.fechaNacimiento = fechaNacimiento;
	    this.direcion = direcion;
	}
	
	

	
	
}
