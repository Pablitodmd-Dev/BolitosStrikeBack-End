package com.example.webthymeleaf.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private double coste;

    private int stock;

    @OneToMany(mappedBy = "recompensa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CanjeRecompensa> canjes = new ArrayList<>();

	public Recompensa(Long id, String nombre, double coste, int stock, List<CanjeRecompensa> canjes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.coste = coste;
		this.stock = stock;
		this.canjes = canjes;
	}

	public Recompensa() {
		super();
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

	public double getCoste() {
		return coste;
	}

	public void setCoste(double coste) {
		this.coste = coste;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<CanjeRecompensa> getCanjes() {
		return canjes;
	}

	public void setCanjes(List<CanjeRecompensa> canjes) {
		this.canjes = canjes;
	}
    
    
}