package com.example.webthymeleaf.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CanjeRecompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaCanje;

    private int cantidadCanjeada;

    private int bolitosGastados;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "recompensa_id", nullable = false)
    private Recompensa recompensa;

	public CanjeRecompensa() {
		super();
	}

	public CanjeRecompensa(Long id, LocalDateTime fechaCanje, int cantidadCanjeada, int bolitosGastados,
			Usuario usuario, Recompensa recompensa) {
		super();
		this.id = id;
		this.fechaCanje = fechaCanje;
		this.cantidadCanjeada = cantidadCanjeada;
		this.bolitosGastados = bolitosGastados;
		this.usuario = usuario;
		this.recompensa = recompensa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaCanje() {
		return fechaCanje;
	}

	public void setFechaCanje(LocalDateTime fechaCanje) {
		this.fechaCanje = fechaCanje;
	}

	public int getCantidadCanjeada() {
		return cantidadCanjeada;
	}

	public void setCantidadCanjeada(int cantidadCanjeada) {
		this.cantidadCanjeada = cantidadCanjeada;
	}

	public int getBolitosGastados() {
		return bolitosGastados;
	}

	public void setBolitosGastados(int bolitosGastados) {
		this.bolitosGastados = bolitosGastados;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Recompensa getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(Recompensa recompensa) {
		this.recompensa = recompensa;
	}
}