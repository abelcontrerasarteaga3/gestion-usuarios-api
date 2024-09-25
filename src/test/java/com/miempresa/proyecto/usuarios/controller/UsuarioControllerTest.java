package com.miempresa.proyecto.usuarios.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.sermaluc.proyecto.usuarios.controller.UsuarioController;
import com.sermaluc.proyecto.usuarios.model.Usuario;
import com.sermaluc.proyecto.usuarios.model.UsuarioResponse;
import com.sermaluc.proyecto.usuarios.service.UsuarioService;

import org.springframework.http.MediaType;


@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;
    @Test
    public void testCrearUsuario_Success() throws Exception {
    	UsuarioResponse usuario = new UsuarioResponse();
        usuario.setEmail("test@example.com");
        usuario.setName("Test Usuario");

        Mockito.when(usuarioService.crearUsuario(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/v1/usuarios/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Usuario\",\"email\":\"test@example.com\",\"password\":\"password123\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    public void testCrearUsuario_EmailAlreadyExists() throws Exception {
        Mockito.when(usuarioService.crearUsuario(any(Usuario.class)))
                .thenThrow(new IllegalArgumentException("El correo ya está registrado"));

        mockMvc.perform(post("/api/v1/usuarios/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test User\",\"email\":\"test@example.com\",\"password\":\"password123\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("El correo ya está registrado"));
    }

}
