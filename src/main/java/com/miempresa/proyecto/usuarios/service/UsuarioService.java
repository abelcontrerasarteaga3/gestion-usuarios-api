package com.miempresa.proyecto.usuarios.service;

import com.miempresa.proyecto.usuarios.model.Usuario;
import com.miempresa.proyecto.usuarios.model.UsuarioResponse;

public interface UsuarioService {
	
	public UsuarioResponse crearUsuario(Usuario usuario);
}
