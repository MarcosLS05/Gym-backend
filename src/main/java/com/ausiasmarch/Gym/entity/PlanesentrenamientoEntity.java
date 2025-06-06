package com.ausiasmarch.Gym.entity;

import java.sql.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "planesentrenamiento")
public class PlanesentrenamientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dificultad", nullable = false) 
    private String dificultad;

    @Column(nullable = false, length = 100) 
    private String titulo;

    @Column(length = 500) 
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @OneToMany(mappedBy = "planesentrenamiento", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<GrupocontrataEntity> grupocontrata;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_creador", nullable = false)
    private UsuarioEntity creador;


    // Constructores
    public PlanesentrenamientoEntity() {
    }

    public PlanesentrenamientoEntity(String dificultad,String titulo, String descripcion, Date fechaCreacion) {  
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.fechaCreacion = fechaCreacion; 
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDificultad() {
    return dificultad;
    }

    public void setDificultad(String dificultad) {
    this.dificultad = dificultad;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public UsuarioEntity getCreador() {
    return creador;
    }

    public void setCreador(UsuarioEntity creador) {
    this.creador = creador;
    }


    public int getGrupocontrata() {
       return grupocontrata.size();
    }

}

