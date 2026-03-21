package com.example.webthymeleaf.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private double beneficio;

    private LocalDateTime fechaInicio;

    private boolean activa;

    private int bolitosRequeridos;

    @OneToMany(mappedBy = "promocion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsoPromocion> usos = new ArrayList<>();

	public Promocion() {
		super();
	}

	public Promocion(Long id, String nombre, String descripcion, double beneficio, LocalDateTime fechaInicio,
			boolean activa, int bolitosRequeridos, List<UsoPromocion> usos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.beneficio = beneficio;
		this.fechaInicio = fechaInicio;
		this.activa = activa;
		this.bolitosRequeridos = bolitosRequeridos;
		this.usos = usos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(double beneficio) {
		this.beneficio = beneficio;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public int getBolitosRequeridos() {
		return bolitosRequeridos;
	}

	public void setBolitosRequeridos(int bolitosRequeridos) {
		this.bolitosRequeridos = bolitosRequeridos;
	}

	public List<UsoPromocion> getUsos() {
		return usos;
	}

	public void setUsos(List<UsoPromocion> usos) {
		this.usos = usos;
	}
    
    
}