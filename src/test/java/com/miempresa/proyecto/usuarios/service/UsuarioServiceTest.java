package com.miempresa.proyecto.usuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sermaluc.proyecto.usuarios.model.Usuario;
import com.sermaluc.proyecto.usuarios.model.UsuarioResponse;
import com.sermaluc.proyecto.usuarios.repository.UsuarioRepository;
import com.sermaluc.proyecto.usuarios.service.UsuarioService;

@SpringBootTest
class UsuarioServiceTest {
	@Autowired
	private UsuarioService usuarioService;
	@MockBean
	private UsuarioRepository usuarioRepository;

	@Test
	public void testCreateUser_Success() {
		Usuario usuario = new Usuario();
		usuario.setEmail("Usuario@example.com");
		usuario.setName("Test Usuario");
		usuario.setPassword("password123");

		Mockito.when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.empty());
		Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

		UsuarioResponse createdUser = usuarioService.crearUsuario(usuario);
		
		assertNotNull(createdUser);
		assertEquals(usuario.getEmail(), createdUser.getEmail());
	}

	@Test
	public void testCreateUsuario_EmailAlreadyExists() {
		Usuario user = new Usuario();
		user.setEmail("test@example.com");

		Mockito.when(usuarioRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			usuarioService.crearUsuario(user);
		});

		assertEquals("El correo ya está registrado", exception.getMessage());
	}
}
