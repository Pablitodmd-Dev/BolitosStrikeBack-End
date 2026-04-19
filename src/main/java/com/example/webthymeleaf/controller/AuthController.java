package com.example.webthymeleaf.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        try {
            String token = authService.login(request.get("username"), request.get("password"));
            Usuario user = userRepository.findByUsername(request.get("username"))
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            return ResponseEntity.ok(Map.of("token", token, "rol", user.getRol()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> register(@RequestBody Map<String, Object> request) {
        Usuario user = new Usuario();
        user.setUsername((String) request.get("username"));
        user.setEmail((String) request.get("email"));
        user.setPassword((String) request.get("password"));
        user.setAceptaTerminos((boolean) request.get("aceptaTerminos"));
        Usuario savedUser = authService.register(user);
        return ResponseEntity.ok(userConverter.entity2dto(savedUser));
    }
    
    @GetMapping("/verify")
    public ResponseEntity<String> verificarEmail(@RequestParam String token) {
        Usuario usuario = userRepository.findByTokenVerificacion(token)
                .orElseThrow(() -> new RuntimeException("Token inválido o expirado"));

        usuario.setEmailVerificado(true);
        usuario.setTokenVerificacion(null);
        userRepository.save(usuario);

        return ResponseEntity.ok(
            "<html><body style='font-family:sans-serif;text-align:center;padding:40px;background:#0F0E17;color:white;'>"
            + "<h1 style='color:#C8920A;'>¡Cuenta verificada!</h1>"
            + "<p>Tu cuenta de BolitosStrike ha sido verificada correctamente.</p>"
            + "<p>Ya puedes iniciar sesión en la aplicación.</p>"
            + "</body></html>"
        );
    }
}
