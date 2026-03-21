package com.example.webthymeleaf.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int numPista;

    @Column(nullable = false)
    private String estado;

    private String descripcion;

    @OneToMany(mappedBy = "pista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();

	public Pista() {
		super();
	}

	public Pista(Long id, int numPista, String estado, String descripcion, List<Reserva> reservas) {
		super();
		this.id = id;
		this.numPista = numPista;
		this.estado = estado;
		this.descripcion = descripcion;
		this.reservas = reservas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumPista() {
		return numPista;
	}

	public void setNumPista(int numPista) {
		this.numPista = numPista;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
    
}