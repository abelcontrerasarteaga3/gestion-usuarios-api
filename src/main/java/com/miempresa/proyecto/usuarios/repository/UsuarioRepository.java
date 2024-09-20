package com.miempresa.proyecto.usuarios.repository;

import java.util.Optional;


import com.miempresa.proyecto.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	 Optional<Usuario> findByEmail(String email);
}
