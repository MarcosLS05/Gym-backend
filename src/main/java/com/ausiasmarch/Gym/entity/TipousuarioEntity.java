package com.ausiasmarch.Gym.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipousuario")
public class TipousuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @OneToMany(mappedBy = "tipousuario", fetch = FetchType.LAZY)
    private java.util.List<UsuarioEntity> usuarios;

    public TipousuarioEntity() {
    }

    public TipousuarioEntity(String titulo) {
        this.titulo = titulo;
    }

    public TipousuarioEntity(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return titulo;
    }

    public void setDescripcion(String titulo) {
        this.titulo = titulo;
    }

    public int getUsuarios() {
        return usuarios.size();
    }
}
