package com.ausiasmarch.Gym.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ausiasmarch.Gym.entity.MensajeEntity;
import java.util.List;
import com.ausiasmarch.Gym.entity.UsuarioEntity;
@Repository
public interface MensajeRepository extends JpaRepository<MensajeEntity, Long> {

    // Obtener mensajes entre dos usuarios, ordenados por fecha ascendente
    @Query("""
        SELECT m FROM Mensaje m
        WHERE (m.emisor.id = :usuario1Id AND m.receptor.id = :usuario2Id)
           OR (m.emisor.id = :usuario2Id AND m.receptor.id = :usuario1Id)
        ORDER BY m.fechaEnvio ASC
    """)
    List<MensajeEntity> findConversacion(Long usuario1Id, Long usuario2Id);

    // Listar usuarios con los que un usuario ha conversado
    @Query("""
        SELECT DISTINCT 
          CASE 
            WHEN m.emisor.id = :userId THEN m.receptor
            ELSE m.emisor
          END
        FROM Mensaje m
        WHERE m.emisor.id = :userId OR m.receptor.id = :userId
    """)
    List<UsuarioEntity> findUsuariosConversados(Long userId);

    // Obtener mensajes no leídos de un receptor
    List<MensajeEntity> findByReceptorIdAndLeidoFalse(Long receptorId);
}
