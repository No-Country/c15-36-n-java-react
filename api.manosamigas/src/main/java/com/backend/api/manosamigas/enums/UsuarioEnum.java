package com.backend.api.manosamigas.enums;

public enum UsuarioEnum {
	ADMIN("admin"),
	USER("user");
	
	private String rol;


	UsuarioEnum(String string) {
		// TODO Auto-generated constructor stub
	}


	void UserRole(String role){
		this.rol = role;
	}


	public String getRole() {
		return rol;
	}
	
	
}
