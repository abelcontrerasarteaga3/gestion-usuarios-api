package com.miempresa.proyecto.usuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.miempresa.proyecto.usuarios.util.JWTUtil;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "name", length = 255)
	private String name;
	
	@Column(name = "email", columnDefinition = "TEXT")
	@Email(message = "El formato del correo electrónico no es válido.")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El formato del correo electrónico debe ser correcto (ejemplo@dominio.cl).")
	private String email;
	
	@Column(name = "password", length = 255)
	@Pattern(regexp = "${password.regex}", message = "El formato de la contraseña no es válido.")
	private String password;
	
	@Column(name = "created", columnDefinition = "TIMESTAMP(6)")
	private Date created;
	
	@Column(name = "modified", columnDefinition = "TIMESTAMP(6)")
	private Date modified;
	
	@Column(name = "last_login", columnDefinition = "TIMESTAMP(6)")
	private Date last_login;
	
	@Column(name = "token", length = 255)
	private String token;
	
	@Column(name = "is_active", nullable = false)
	private boolean isActive;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Telefono> phones;
	
	
	@PrePersist
	protected void onCreate() {
		this.created = new Date();
		this.modified = new Date();
		this.last_login = new Date();
		this.isActive = true;
		JWTUtil jwtUtil=new JWTUtil(); 
		this.token = jwtUtil.generateToken(email);
	}

	@PreUpdate
	protected void onUpdate() {
		this.modified = new Date();
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<Telefono> getPhones() {
		return phones;
	}

	public void setPhones(List<Telefono> phones) {
		this.phones = phones;
	}

}
