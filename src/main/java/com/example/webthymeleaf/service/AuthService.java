package com.example.webthymeleaf.service;

import com.example.webthymeleaf.entity.Usuario;
import com.example.webthymeleaf.repository.UsuarioRepository;
import com.example.webthymeleaf.security.JwtTokenProvider;
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

    public String login(String username, String password) {
        System.out.println("Intentando login con username: " + username);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("Login exitoso para: " + username);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return tokenProvider.generateToken(authentication);
        } catch (Exception e) {
            System.out.println("Error en login: " + e.getMessage());
            throw e;
        }
    }

    public Usuario register(Usuario usuario) {
        if (userRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken!");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol("ROLE_USER");
        usuario.setTotalBolitos(0);
        usuario.setDeleted(false);
        return userRepository.save(usuario);
    }
}
