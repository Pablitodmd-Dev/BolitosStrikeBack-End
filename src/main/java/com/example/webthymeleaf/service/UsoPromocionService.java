package com.example.webthymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.entity.Promocion;
import com.example.webthymeleaf.entity.UsoPromocion;
import com.example.webthymeleaf.entity.Usuario;
import com.example.webthymeleaf.repository.UsoPromocionRepository;

@Service
public class UsoPromocionService {

    @Autowired
    private UsoPromocionRepository usoPromocionRepository;

    @Autowired
    private PromocionService promocionService;

    @Autowired
    private UsuarioService usuarioService;

    public List<UsoPromocion> getUsosByUsuario(Long usuarioId) {
        return usoPromocionRepository.findByUsuarioId(usuarioId);
    }

    public UsoPromocion usarPromocion(UsoPromocion uso) {
        Usuario usuario = usuarioService.getUsuarioById(uso.getUsuario().getId());
        Promocion promocion = promocionService.getPromocionById(uso.getPromocion().getId());

        if (!promocion.isActiva()) {
            throw new RuntimeException("La promocion no está activa");
        }

        if (usuario.getTotalBolitos() < promocion.getBolitosRequeridos()) {
            throw new RuntimeException("Bolitos insuficientes para usar esta promocion");
        }

        if (usoPromocionRepository.findByUsuarioIdAndPromocionId(usuario.getId(), promocion.getId()).isPresent()) {
            throw new RuntimeException("Ya has usado esta promocion");
        }

        return usoPromocionRepository.save(uso);
    }
}