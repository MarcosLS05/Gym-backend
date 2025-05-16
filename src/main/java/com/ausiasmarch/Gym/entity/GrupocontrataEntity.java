package com.ausiasmarch.Gym.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "grupocontrata")
public class GrupocontrataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull 
    @Size(min = 3, max = 255)
    private String titulo;

    @NotNull
    private String descripcion;

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario; 

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_planesentrenamiento")
    private PlanesentrenamientoEntity planesentrenamiento; 

    // Constructores
    public GrupocontrataEntity() {
    }

    public GrupocontrataEntity(String titulo, String descripcion) {
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
