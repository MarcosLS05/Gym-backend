package com.ausiasmarch.Gym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ausiasmarch.Gym.entity.TipousuarioEntity;
public interface TipousuarioRepository extends JpaRepository<TipousuarioEntity, Long> {

    Page<TipousuarioEntity> findByTituloContaining(String filter, Pageable oPageable);
}
