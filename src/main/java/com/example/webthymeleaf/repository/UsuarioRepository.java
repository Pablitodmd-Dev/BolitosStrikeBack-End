package com.example.webthymeleaf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webthymeleaf.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	Optional<Usuario> findByUsername(String username);
	Optional<Usuario> findByTokenVerificacion(String token);
}
