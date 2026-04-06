package com.example.webthymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.converter.UsuarioConverter;
import com.example.webthymeleaf.entity.Usuario;
import com.example.webthymeleaf.model.UsuarioDTO;
import com.example.webthymeleaf.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioConverter usuarioConverter;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<UsuarioDTO> getAllUsuariosDTO() {
        return usuarioConverter.entityList2dtoList(getAllUsuarios());
    }

    public Usuario getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getDeleted()) {
            throw new RuntimeException("Usuario eliminado");
        }

        return usuario;
    }

    public UsuarioDTO getUsuarioByIdDTO(Long id) {
        return usuarioConverter.entity2dto(getUsuarioById(id));
    }

    public Usuario getUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        Usuario usuario = getUsuarioById(id);
        usuario.setUsername(usuarioDetails.getUsername());
        usuario.setEmail(usuarioDetails.getEmail());

        if (usuarioDetails.getPassword() != null && !usuarioDetails.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuarioDetails.getPassword()));
        }

        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = getUsuarioById(id);
        usuario.setDeleted(true);
        usuarioRepository.save(usuario);
    }
}