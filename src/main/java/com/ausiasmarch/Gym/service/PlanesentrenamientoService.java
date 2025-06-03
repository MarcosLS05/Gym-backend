package com.ausiasmarch.Gym.service;


import java.util.List;
import java.util.Optional;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ausiasmarch.Gym.entity.PlanesentrenamientoEntity;
import com.ausiasmarch.Gym.exception.ResourceNotFoundException;
import com.ausiasmarch.Gym.exception.UnauthorizedAccessException;
import com.ausiasmarch.Gym.repository.PlanesentrenamientoRepository;


@Service
public class PlanesentrenamientoService {

    @Autowired
    private RandomService oRandomService;
    @Autowired
    private PlanesentrenamientoRepository oPlanesentrenamientoRepository;

    @Autowired
    private AuthService oAuthService;


    private String[] difficultyLevels = { "Principiante", "Intermedio", "Avanzado" };

    // Obtener un plan por ID
    public PlanesentrenamientoEntity get(Long id) {
        return oPlanesentrenamientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan de entrenamiento no encontrado: " + id));
    }

    public List<PlanesentrenamientoEntity> findByCreadorId(Long idCreador) {
    return oPlanesentrenamientoRepository.findByCreadorId(idCreador);
}


    // Obtener una página de planes
    public Page<PlanesentrenamientoEntity> getPage(Pageable pageable, Optional<String> filter) {
        if (filter.isPresent() && !filter.get().isBlank()) {
            String filtro = filter.get();
            return oPlanesentrenamientoRepository.findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCaseOrDificultadContainingIgnoreCase(
                    filtro, filtro, filtro, pageable);
        } else {
            return oPlanesentrenamientoRepository.findAll(pageable);
        }
    }   

    public Long randomCreate(Long cantidad) {
        Long count = 0L;
        for (int i = 0; i < cantidad; i++) {
            PlanesentrenamientoEntity planesentrenamientoEntity = new PlanesentrenamientoEntity();
            planesentrenamientoEntity.setDificultad(difficultyLevels[oRandomService.getRandomInt(0, difficultyLevels.length - 1)]);
            planesentrenamientoEntity.setTitulo("Plan de entrenamiento " + i);
            planesentrenamientoEntity.setDescripcion("Descripción del plan de entrenamiento " + i);
            
            oPlanesentrenamientoRepository.save(planesentrenamientoEntity);
            count++;
        }
        return count;
    }
    
    public PlanesentrenamientoEntity randomSelection() {
        Long count = oPlanesentrenamientoRepository.count();
        if (count == 0) {
            throw new ResourceNotFoundException("No hay registros en la tabla planesentrenamiento.");
        }
        int randomIndex = oRandomService.getRandomInt(0, count.intValue() - 1);
        Pageable pageable = Pageable.ofSize(1).withPage(randomIndex);
        Page<PlanesentrenamientoEntity> page = oPlanesentrenamientoRepository.findAll(pageable);
        return page.getContent().get(0);
    }
    

    // Crear un nuevo plan
    public PlanesentrenamientoEntity create(PlanesentrenamientoEntity oPlanesentrenamientoEntity) {
        if (oAuthService.isAdmin() || oAuthService.isEntrenadorPersonal()) {
            // Asignar la fecha de creación al plan
            oPlanesentrenamientoEntity.setFechaCreacion(new Date(System.currentTimeMillis()));
            return oPlanesentrenamientoRepository.save(oPlanesentrenamientoEntity);
        } else {
            throw new UnauthorizedAccessException("No tienes permiso para crear planes de entrenamiento.");
        }
    }



    

    // Eliminar un plan por ID
    public Long delete(Long id) {
        if (oAuthService.isAdmin() || oAuthService.isEntrenadorPersonal()) {
            oPlanesentrenamientoRepository.deleteById(id);
            return 1L;
        }else {
            throw new UnauthorizedAccessException("No tienes permiso para borrar el plan de entrenamiento.");
        }

    }

    // Contar el total de planes
    public Long count() {
        return oPlanesentrenamientoRepository.count();
    }

    // Eliminar todos los planes
    public void deleteAll() {
        if (!oAuthService.isAdmin()){
            oPlanesentrenamientoRepository.deleteAll();
        }else{
            throw new UnauthorizedAccessException("No tienes permiso para borrar todos los planes de entrenamiento.");
        }
        
    }

    public PlanesentrenamientoEntity update(PlanesentrenamientoEntity oPlanesentrenamientoEntity) {
        if (oAuthService.isAdmin() ||oAuthService.isEntrenadorPersonal()) {
                    // Buscar la entidad existente en la base de datos por su ID
        PlanesentrenamientoEntity oPlanesentrenamientoEntityFromDatabase = 
        oPlanesentrenamientoRepository.findById(oPlanesentrenamientoEntity.getId())
            .orElseThrow(() -> new ResourceNotFoundException(
                "Plan de entrenamiento con ID " + oPlanesentrenamientoEntity.getId() + " no encontrado"));
        // Actualizar el campo 'dificultad' si no es nulo
        if (oPlanesentrenamientoEntity.getDificultad() != null) {
            oPlanesentrenamientoEntityFromDatabase.setDificultad(oPlanesentrenamientoEntity.getDificultad());
        }
        // Actualizar el campo 'titulo' si no es nulo
        if (oPlanesentrenamientoEntity.getTitulo() != null) {
            oPlanesentrenamientoEntityFromDatabase.setTitulo(oPlanesentrenamientoEntity.getTitulo());
        }
    
        // Actualizar el campo 'descripcion' si no es nulo
        if (oPlanesentrenamientoEntity.getDescripcion() != null) {
            oPlanesentrenamientoEntityFromDatabase.setDescripcion(oPlanesentrenamientoEntity.getDescripcion());
        } 
        // Guardar y devolver la entidad actualizada
        return oPlanesentrenamientoRepository.save(oPlanesentrenamientoEntityFromDatabase);
            
        } else {
            throw new UnauthorizedAccessException("No tienes permiso para modificar el plan de entrenamiento.");
        }

    }
}

