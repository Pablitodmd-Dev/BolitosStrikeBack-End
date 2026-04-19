package com.example.webthymeleaf.service;

import com.example.webthymeleaf.entity.Usuario;
import com.example.webthymeleaf.repository.UsuarioRepository;
import com.example.webthymeleaf.security.JwtTokenProvider;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UsuarioRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private EmailService emailService;

	public String login(String username, String password) {
		Usuario usuario = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		if (!usuario.isEmailVerificado()) {
			throw new RuntimeException("Debes verificar tu email antes de iniciar sesión");
		}
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return tokenProvider.generateToken(authentication);
	}

	public Usuario register(Usuario usuario) {
		if (userRepository.findByUsername(usuario.getUsername()).isPresent()) {
			throw new RuntimeException("Username is already taken!");
		}
		String token = UUID.randomUUID().toString();
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setRol("ROLE_USER");
		usuario.setTotalBolitos(0);
		usuario.setDeleted(false);
		usuario.setEmailVerificado(false);
		usuario.setTokenVerificacion(token);

		Usuario savedUser = userRepository.save(usuario);

		emailService.enviarEmailVerificacion(usuario.getEmail(), token);

		return savedUser;
	}
}
