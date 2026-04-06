package com.example.webthymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.entity.HistorialBolitos;
import com.example.webthymeleaf.entity.Usuario;
import com.example.webthymeleaf.repository.HistorialBolitosRepository;

@Service
public class HistorialBolitosService {

    @Autowired
    private HistorialBolitosRepository historialBolitosRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<HistorialBolitos> getHistorialByUsuario(Long usuarioId) {
        return historialBolitosRepository.findByUsuarioId(usuarioId);
    }

    public List<HistorialBolitos> getHistorialByTipo(Long usuarioId, String tipo) {
        return historialBolitosRepository.findByUsuarioIdAndTipoMovimiento(usuarioId, tipo);
    }

    public HistorialBolitos registrarMovimiento(HistorialBolitos movimiento) {
        Usuario usuario = movimiento.getUsuario();
        usuario.setTotalBolitos(usuario.getTotalBolitos() + movimiento.getCantidad());
        usuarioService.updateUsuario(usuario.getId(), usuario);
        return historialBolitosRepository.save(movimiento);
    }
}