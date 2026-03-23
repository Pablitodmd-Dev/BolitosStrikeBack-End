package com.example.webthymeleaf.converter;

import com.example.webthymeleaf.model.UsuarioDTO;
import com.example.webthymeleaf.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioConverter {

    public UsuarioDTO entity2dto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());
        dto.setAceptaTerminos(usuario.isAceptaTerminos());
        dto.setTotalBolitos(usuario.getTotalBolitos());
        dto.setDeleted(usuario.getDeleted());
        return dto;
    }

    public List<UsuarioDTO> entityList2dtoList(List<Usuario> usuarios) {
        if (usuarios == null) return List.of();
        return usuarios.stream()
            .map(this::entity2dto)
            .collect(Collectors.toList());
    }

    public Usuario dto2entity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setUsername(dto.getUsername());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());
        usuario.setAceptaTerminos(dto.isAceptaTerminos());
        usuario.setTotalBolitos(dto.getTotalBolitos());
        usuario.setDeleted(dto.getDeleted());
        return usuario;
    }
}