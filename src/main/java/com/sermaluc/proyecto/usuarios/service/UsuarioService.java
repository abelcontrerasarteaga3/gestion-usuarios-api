package com.sermaluc.proyecto.usuarios.service;

import com.sermaluc.proyecto.usuarios.model.Usuario;
import com.sermaluc.proyecto.usuarios.model.UsuarioResponse;

public interface UsuarioService {
	
	public UsuarioResponse crearUsuario(Usuario usuario);
}
