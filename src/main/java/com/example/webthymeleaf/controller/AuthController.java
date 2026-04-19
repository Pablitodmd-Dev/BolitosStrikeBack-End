package com.example.webthymeleaf.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webthymeleaf.converter.UsuarioConverter;
import com.example.webthymeleaf.entity.Usuario;
import com.example.webthymeleaf.model.UsuarioDTO;
import com.example.webthymeleaf.repository.UsuarioRepository;
import com.example.webthymeleaf.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioConverter userConverter;
    
    @Autowired
    private UsuarioRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String token = authService.login(request.get("username"), request.get("password"));
        Usuario user=userRepository.findByUsername(request.get("username")).orElseThrow(
        		() -> new UsernameNotFoundException("Usuario no encontrado"));
        return ResponseEntity.ok(Map.of("token", token, "rol", user.getRol()));
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> register(@RequestBody Usuario user) {
        Usuario savedUser = authService.register(user);
        return ResponseEntity.ok(userConverter.entity2dto(savedUser));
    }
}
