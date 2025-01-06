package com.ausiasmarch.Gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.ausiasmarch.Gym.entity.PlanesentrenamientoEntity;

@Repository
public interface PlanesentrenamientoRepository extends JpaRepository<PlanesentrenamientoEntity, Long> {

    // Buscar planes por título (case-insensitive)
    Page<PlanesentrenamientoEntity> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    // Buscar planes por descripción (case-insensitive)
    Page<PlanesentrenamientoEntity> findByDescripcionContainingIgnoreCase(String descripcion, Pageable pageable);

    // Buscar por título o descripción (case-insensitive)
    Page<PlanesentrenamientoEntity> findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(
        String titulo, String descripcion, Pageable pageable);
}

