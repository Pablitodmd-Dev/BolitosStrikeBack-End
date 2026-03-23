package com.example.webthymeleaf.model;

public class UsuarioDTO {

    private Long id;
    private String username;
    private String email;
    private String rol;
    private boolean aceptaTerminos;
    private int totalBolitos;
    private Boolean deleted;

    public UsuarioDTO() {}

    public UsuarioDTO(Long id, String username, String email, String rol, boolean aceptaTerminos, int totalBolitos, Boolean deleted) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.rol = rol;
        this.aceptaTerminos = aceptaTerminos;
        this.totalBolitos = totalBolitos;
        this.deleted = deleted;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public boolean isAceptaTerminos() { return aceptaTerminos; }
    public void setAceptaTerminos(boolean aceptaTerminos) { this.aceptaTerminos = aceptaTerminos; }

    public int getTotalBolitos() { return totalBolitos; }
    public void setTotalBolitos(int totalBolitos) { this.totalBolitos = totalBolitos; }

    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
}