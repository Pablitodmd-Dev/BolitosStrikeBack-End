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

        System.out.println("Usuario antes: " + usuario.getUsername() + " pass: " + usuario.getPassword() + " bolitos: " + usuario.getTotalBolitos());

        if (usuario.getTotalBolitos() < recompensa.getCoste()) {
            throw new RuntimeException("Bolitos insuficientes para canjear esta recompensa");
        }

        if (recompensa.getStock() <= 0) {
            throw new RuntimeException("Recompensa sin stock disponible");
        }

        usuario.setTotalBolitos((int)(usuario.getTotalBolitos() - recompensa.getCoste()));
        recompensa.setStock(recompensa.getStock() - 1);

        usuarioService.updateUsuario(usuario.getId(), usuario);
        
        System.out.println("Usuario después: " + usuario.getUsername() + " pass: " + usuario.getPassword() + " bolitos: " + usuario.getTotalBolitos());
        
        recompensaService.updateRecompensa(recompensa.getId(), recompensa);
        return canjeRecompensaRepository.save(canje);
    }
    
    public CanjeRecompensa canjearRecompensaDTO(com.example.webthymeleaf.model.CanjeRecompensaRequestDTO dto) {
        CanjeRecompensa canje = new CanjeRecompensa();
        canje.setCantidadCanjeada(dto.getCantidadCanjeada());
        canje.setBolitosGastados(dto.getBolitosGastados());
        canje.setFechaCanje(dto.getFechaCanje());
        canje.setUsuario(usuarioService.getUsuarioById(dto.getUsuarioId()));
        canje.setRecompensa(recompensaService.getRecompensaById(dto.getRecompensaId()));
        return canjearRecompensa(canje);
    }
}