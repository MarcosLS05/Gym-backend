package com.ausiasmarch.Gym.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ausiasmarch.Gym.entity.PlanesentrenamientoEntity;
import com.ausiasmarch.Gym.entity.UsuarioEntity;
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

    // Obtener un plan por ID
    public PlanesentrenamientoEntity get(Long id) {
        return oPlanesentrenamientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan de entrenamiento no encontrado: " + id));
    }

    // Obtener una página de planes
    public Page<PlanesentrenamientoEntity> getPage(Pageable pageable, Optional<String> filter) {
        if (filter.isPresent() && !filter.get().isBlank()) {
            String filtro = filter.get();
            return oPlanesentrenamientoRepository.findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(
                    filtro, filtro, pageable);
        } else {
            return oPlanesentrenamientoRepository.findAll(pageable);
        }
    }

    public Long randomCreate(Long cantidad) {
        Long count = 0L;
        for (int i = 0; i < cantidad; i++) {
            PlanesentrenamientoEntity planesentrenamientoEntity = new PlanesentrenamientoEntity();
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
        return oPlanesentrenamientoRepository.save(oPlanesentrenamientoEntity);
    }

    

    // Eliminar un plan por ID
    public Long delete(Long id) {
        oPlanesentrenamientoRepository.deleteById(id);
        return 1L;
    }

    // Contar el total de planes
    public Long count() {
        return oPlanesentrenamientoRepository.count();
    }

    // Eliminar todos los planes
    public void deleteAll() {
        oPlanesentrenamientoRepository.deleteAll();
    }

    public PlanesentrenamientoEntity update(PlanesentrenamientoEntity oPlanesentrenamientoEntity) {
        // Buscar la entidad existente en la base de datos por su ID
        PlanesentrenamientoEntity oPlanesentrenamientoEntityFromDatabase = 
        oPlanesentrenamientoRepository.findById(oPlanesentrenamientoEntity.getId())
            .orElseThrow(() -> new ResourceNotFoundException(
                "Plan de entrenamiento con ID " + oPlanesentrenamientoEntity.getId() + " no encontrado"));
    
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
    }
    
}

