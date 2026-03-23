package com.example.webthymeleaf.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String rol;

    private boolean aceptaTerminos;

    private int totalBolitos;
    
    @Column(nullable = false)
    private Boolean deleted=false;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipacionEvento> participaciones = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notificacion> notificaciones = new ArrayList<>();
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CanjeRecompensa> canjes = new ArrayList<>();
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistorialBolitos> historialBolitos = new ArrayList<>();
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsoPromocion> usos = new ArrayList<>();
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();

    public Usuario() {
		super();
	}

	public Usuario(Long id, String username, String email, String password, String rol, boolean aceptaTerminos,
			int totalBolitos, Boolean deleted, List<ParticipacionEvento> participaciones,
			List<Notificacion> notificaciones, List<CanjeRecompensa> canjes, List<HistorialBolitos> historialBolitos,
			List<UsoPromocion> usos, List<Reserva> reservas) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.aceptaTerminos = aceptaTerminos;
		this.totalBolitos = totalBolitos;
		this.deleted = deleted;
		this.participaciones = participaciones;
		this.notificaciones = notificaciones;
		this.canjes = canjes;
		this.historialBolitos = historialBolitos;
		this.usos = usos;
		this.reservas = reservas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public boolean isAceptaTerminos() {
		return aceptaTerminos;
	}

	public void setAceptaTerminos(boolean aceptaTerminos) {
		this.aceptaTerminos = aceptaTerminos;
	}

	public int getTotalBolitos() {
		return totalBolitos;
	}

	public void setTotalBolitos(int totalBolitos) {
		this.totalBolitos = totalBolitos;
	}

	public List<ParticipacionEvento> getParticipaciones() {
		return participaciones;
	}

	public void setParticipaciones(List<ParticipacionEvento> participaciones) {
		this.participaciones = participaciones;
	}

	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public List<CanjeRecompensa> getCanjes() {
		return canjes;
	}

	public void setCanjes(List<CanjeRecompensa> canjes) {
		this.canjes = canjes;
	}

	public List<HistorialBolitos> getHistorialBolitos() {
		return historialBolitos;
	}

	public void setHistorialBolitos(List<HistorialBolitos> historialBolitos) {
		this.historialBolitos = historialBolitos;
	}

	public List<UsoPromocion> getUsos() {
		return usos;
	}

	public void setUsos(List<UsoPromocion> usos) {
		this.usos = usos;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	 public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}