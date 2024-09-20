package com.miempresa.proyecto.usuarios.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.miempresa.proyecto.usuarios.exception.EmailAlreadyRegisteredException;
import com.miempresa.proyecto.usuarios.exception.InvalidEmailFormatException;
import com.miempresa.proyecto.usuarios.exception.InvalidPasswordFormatException;
import com.miempresa.proyecto.usuarios.model.Usuario;
import com.miempresa.proyecto.usuarios.model.UsuarioResponse;
import com.miempresa.proyecto.usuarios.repository.UsuarioRepository;
import com.miempresa.proyecto.usuarios.service.UsuarioService;
import com.miempresa.proyecto.usuarios.util.JWTUtil;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Value("${clave.regex}")
	private String claveRegex;

	@Value("${correo.regex}")
	private String correoRegex;

	@Override
	public UsuarioResponse crearUsuario(Usuario usuario) {
		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
			throw new EmailAlreadyRegisteredException("El correo ya está registrado");
		}

		if (!usuario.getPassword().matches(claveRegex)) { // Reemplaza "claveRegex" con tu regex real
			throw new InvalidPasswordFormatException("El formato de la password no es válido.");
		}

		if (!usuario.getEmail().matches(correoRegex)) { // Reemplaza "correoRegex" con tu regex real
			throw new InvalidEmailFormatException(
					"El formato del correo electrónico no es válido.");
		}
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setName(usuario.getName());
		nuevoUsuario.setEmail(usuario.getEmail());
		nuevoUsuario.setPassword(usuario.getPassword());
		nuevoUsuario.setPhones(usuario.getPhones());
		nuevoUsuario.setToken(usuario.getToken());
		String id = UUID.randomUUID().toString();
		nuevoUsuario.setId(id);
		Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);
		return convertirAUsuarioResponse(usuarioGuardado);
	}

	private UsuarioResponse convertirAUsuarioResponse(Usuario usuario) {
		UsuarioResponse response = new UsuarioResponse();
		response.setId(usuario.getId());
		response.setCreated(usuario.getCreated());
		response.setModified(usuario.getModified());
		response.setLast_login(usuario.getLast_login());
		response.setToken(usuario.getToken());
		response.setIsactive(usuario.getIsActive());
		return response;
	}
}
