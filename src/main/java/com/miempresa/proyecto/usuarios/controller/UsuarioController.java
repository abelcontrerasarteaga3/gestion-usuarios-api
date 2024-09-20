package com.miempresa.proyecto.usuarios.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miempresa.proyecto.usuarios.exception.EmailAlreadyRegisteredException;
import com.miempresa.proyecto.usuarios.exception.InvalidEmailFormatException;
import com.miempresa.proyecto.usuarios.exception.InvalidPasswordFormatException;
import com.miempresa.proyecto.usuarios.model.Usuario;
import com.miempresa.proyecto.usuarios.model.UsuarioResponse;
import com.miempresa.proyecto.usuarios.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/crear")
	public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
		try {
			UsuarioResponse usuarioResponse = usuarioService.crearUsuario(usuario);
			return ResponseEntity.status(HttpStatus.OK).body(usuarioResponse);
		} catch (EmailAlreadyRegisteredException e) {
			// Código 409 Conflict para indicar que el correo ya está registrado
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("mensaje", e.getMessage()));
		} catch (InvalidPasswordFormatException e) {
			// Código 400 Bad Request para indicar formato de contraseña inválido
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("mensaje", e.getMessage()));
		} catch (InvalidEmailFormatException e) {
			// Código 400 Bad Request para indicar formato de correo inválido
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("mensaje", e.getMessage()));
		} catch (Exception e) {
			// Código 500 Internal Server Error para errores no manejados
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonMap("mensaje", "Error interno del servidor: " + e.getMessage()));
		}
	}
}
