package com.ausiasmarch.Gym.entity;


import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "grupocontrata")
public class GrupocontrataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "creadoEn", nullable = false)
    private Date creadoEn;



    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario; 

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_planesentrenamiento")
    private PlanesentrenamientoEntity planesentrenamiento; 

    // Constructores
    public GrupocontrataEntity() {
    }

    public GrupocontrataEntity(Date creadoEn) {
        this.creadoEn = creadoEn;

    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getCreadoEn() {
        return creadoEn;
    }
    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) { 
        this.usuario = usuario;
    }

    public PlanesentrenamientoEntity getPlanesentrenamiento() {
        return planesentrenamiento;
    }

    public void setPlanesentrenamiento(PlanesentrenamientoEntity planesentrenamiento) { // Corregir asignación
        this.planesentrenamiento = planesentrenamiento;
    }
}
