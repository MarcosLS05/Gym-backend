package com.ausiasmarch.Gym.entity;

import java.util.List;

import com.ausiasmarch.Gym.api.Grupocontrata;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "planesentrenamiento")
public class PlanesentrenamientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100) // Campo obligatorio, máx. 100 caracteres
    private String titulo;

    @Column(length = 500) // Campo opcional, máx. 500 caracteres
    private String descripcion;

    @OneToMany(mappedBy = "planesentrenamiento", fetch = FetchType.LAZY)
    private List<GrupocontrataEntity> grupocontrata;


    // Constructores
    public PlanesentrenamientoEntity() {
    }

    public PlanesentrenamientoEntity(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
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

    public int getGrupocontrata() {
        return grupocontrata.size();
    }

}

