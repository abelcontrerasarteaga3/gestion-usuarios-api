package com.sermaluc.proyecto.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sermaluc.proyecto.usuarios.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	 Optional<Usuario> findByEmail(String email);
}
