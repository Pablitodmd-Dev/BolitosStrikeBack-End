package com.example.webthymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.entity.CanjeRecompensa;
import com.example.webthymeleaf.entity.Recompensa;
import com.example.webthymeleaf.entity.Usuario;
import com.example.webthymeleaf.repository.CanjeRecompensaRepository;

@Service
public class CanjeRecompensaService {

    @Autowired
    private CanjeRecompensaRepository canjeRecompensaRepository;

    @Autowired
    private RecompensaService recompensaService;

    @Autowired
    private UsuarioService usuarioService;

    public List<CanjeRecompensa> getCanjesByUsuario(Long usuarioId) {
        return canjeRecompensaRepository.findByUsuarioId(usuarioId);
    }

    public CanjeRecompensa canjearRecompensa(CanjeRecompensa canje) {
        Usuario usuario = usuarioService.getUsuarioById(canje.getUsuario().getId());
        Recompensa recompensa = recompensaService.getRecompensaById(canje.getRecompensa().getId());

        if (usuario.getTotalBolitos() < recompensa.getCoste()) {
            throw new RuntimeException("Bolitos insuficientes para canjear esta recompensa");
        }

        if (recompensa.getStock() <= 0) {
            throw new RuntimeException("Recompensa sin stock disponible");
        }

        usuario.setTotalBolitos((int)(usuario.getTotalBolitos() - recompensa.getCoste()));
        recompensa.setStock(recompensa.getStock() - 1);

        usuarioService.updateUsuario(usuario.getId(), usuario);
        recompensaService.updateRecompensa(recompensa.getId(), recompensa);

        return canjeRecompensaRepository.save(canje);
    }
}