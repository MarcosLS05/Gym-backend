package com.ausiasmarch.Gym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ausiasmarch.Gym.entity.GrupocontrataEntity;

@Repository
public interface GrupocontrataRepository extends JpaRepository<GrupocontrataEntity, Long> {

    Page<GrupocontrataEntity> findByUsuarioId(Long id, Pageable oPageable);

    @Query(value = "SELECT * FROM asiento WHERE (titulo LIKE %:strTitulo% OR descripcion LIKE %:strDescripcion%) AND id_usuario=:id_usuario", nativeQuery = true)
    Page<GrupocontrataEntity> findByUsuarioIdAndTituloContainingOrDescripcionContaining(String strTitulo,
        String strDescripcion, Long id_usuario, Pageable oPageable);

    // Método personalizado corregido
    Page<GrupocontrataEntity> findByUsuarioNombreContainingOrPlanesentrenamientoTituloContaining(
        String usuarioNombre, String planesentrenamientoTitulo, Pageable pageable);
    
    @Query(value = "SELECT * FROM grupocontrata ORDER BY RAND() LIMIT 1", nativeQuery = true)
    GrupocontrataEntity findRandom();
}

