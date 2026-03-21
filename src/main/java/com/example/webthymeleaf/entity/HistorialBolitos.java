package com.example.webthymeleaf.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HistorialBolitos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoMovimiento;

    private int cantidad;

    private LocalDateTime fecha;

    private String concepto;

    @Column(nullable = true)
    private Long referenciaId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

	public HistorialBolitos() {
		super();
	}

	public HistorialBolitos(Long id, String tipoMovimiento, int cantidad, LocalDateTime fecha, String concepto,
			Long referenciaId, Usuario usuario) {
		super();
		this.id = id;
		this.tipoMovimiento = tipoMovimiento;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.concepto = concepto;
		this.referenciaId = referenciaId;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Long getReferenciaId() {
		return referenciaId;
	}

	public void setReferenciaId(Long referenciaId) {
		this.referenciaId = referenciaId;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
}