package com.ausiasmarch.Gym.entity;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mensaje")
public class MensajeEntity {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "emisor_id", nullable = false)
    private UsuarioEntity emisor;

    @ManyToOne
    @JoinColumn(name = "receptor_id", nullable = false)
    private UsuarioEntity receptor;

    @Column(nullable = false)
    private String contenido;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio;

    @Column(nullable = false)
    private boolean leido = false;

    
    
    
    
    public MensajeEntity() {
    }

    public MensajeEntity(Long id, UsuarioEntity emisor, UsuarioEntity receptor, String contenido,
            LocalDateTime fechaEnvio, boolean leido) {
        this.id = id;
        this.emisor = emisor;
        this.receptor = receptor;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
        this.leido = leido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioEntity getEmisor() {
        return emisor;
    }

    public void setEmisor(UsuarioEntity emisor) {
        this.emisor = emisor;
    }

    public UsuarioEntity getReceptor() {
        return receptor;
    }

    public void setReceptor(UsuarioEntity receptor) {
        this.receptor = receptor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

   
}

