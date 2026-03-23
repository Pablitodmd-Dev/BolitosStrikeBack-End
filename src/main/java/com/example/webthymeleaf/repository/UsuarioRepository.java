package com.example.webthymeleaf.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.example.webthymeleaf.entity.Usuario;

@Repository
public interface UsuarioRepository {
	Optional<Usuario> findByUsername(String username);
}
