package com.ausiasmarch.Gym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.ausiasmarch.Gym.entity.UsuarioEntity;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);

    Optional<UsuarioEntity> findByEmailAndPassword(String email, String password);
    
    Page<UsuarioEntity> findByTipousuarioId(Long id_tipousuario, Pageable oPageable);

    @Query(value = "SELECT * FROM usuario WHERE (nombre LIKE %:strNombre% OR apellido1 LIKE %:strApellido1% OR apellido2 LIKE %:strApellido2% OR email LIKE %:strEmail%) AND id_tipousuario=:id_tipousuario", nativeQuery = true)
    Page<UsuarioEntity> findByTipousuarioIdAndTituloContaining(Long id_tipousuario, String filter1, Pageable oPageable);
    
    Page<UsuarioEntity> findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContaining(
            String filter2, String filter3, String filter4, String filter5, Pageable oPageable);




}