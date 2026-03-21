package com.example.webthymeleaf.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UsoPromocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaUso;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "promocion_id", nullable = false)
    private Promocion promocion;

	public UsoPromocion() {
		super();
	}

	public UsoPromocion(Long id, LocalDateTime fechaUso, Usuario usuario, Promocion promocion) {
		super();
		this.id = id;
		this.fechaUso = fechaUso;
		this.usuario = usuario;
		this.promocion = promocion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaUso() {
		return fechaUso;
	}

	public void setFechaUso(LocalDateTime fechaUso) {
		this.fechaUso = fechaUso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}
    
    
}