package com.ausiasmarch.Gym.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ausiasmarch.Gym.entity.GrupocontrataEntity;
import com.ausiasmarch.Gym.entity.PlanesentrenamientoEntity;
import com.ausiasmarch.Gym.entity.UsuarioEntity;
import com.ausiasmarch.Gym.repository.UsuarioRepository;
import com.ausiasmarch.Gym.repository.PlanesentrenamientoRepository;
import com.ausiasmarch.Gym.exception.ResourceNotFoundException;
import com.ausiasmarch.Gym.repository.GrupocontrataRepository;

@Service
public class GrupocontrataService implements ServiceInterface<GrupocontrataEntity> {

    @Autowired
    private GrupocontrataRepository grupocontrataRepository;

    @Autowired
    private UsuarioRepository UsuarioRepository;

    @Autowired
    private PlanesentrenamientoRepository PlanesentrenamientoRepository;

    @Autowired
    private RandomService oRandomService;

    @Autowired
    private UsuarioService oUsuarioService;

    @Autowired
    private PlanesentrenamientoService oPlanesentrenamientoService;

    @Override
    public Long randomCreate(Long cantidad) {
        Long count = 0L;
        for (int i = 0; i < cantidad; i++) {
            GrupocontrataEntity grupocontrataEntity = new GrupocontrataEntity();
            
            // Asignar valores para los campos obligatorios
            grupocontrataEntity.setTitulo("Título aleatorio " + i);
            grupocontrataEntity.setDescripcion("Descripción aleatoria " + i);
            
            // Relacionar con un usuario y un plan de entrenamiento aleatorios
            grupocontrataEntity.setUsuario(oUsuarioService.randomSelection());
            grupocontrataEntity.setPlanesentrenamiento(oPlanesentrenamientoService.randomSelection());
            
            // Guardar la entidad
            grupocontrataRepository.save(grupocontrataEntity);
            count++;
        }
        return count;
    }

    @Override
    public GrupocontrataEntity randomSelection() {
        Long count = grupocontrataRepository.count();
        if (count == 0) {
            throw new ResourceNotFoundException("No hay registros en la tabla grupocontrata.");
        }
        int randomIndex = oRandomService.getRandomInt(0, count.intValue() - 1);
        Pageable pageable = Pageable.ofSize(1).withPage(randomIndex);
        Page<GrupocontrataEntity> page = grupocontrataRepository.findAll(pageable);
        return page.getContent().get(0);
    }

    @Override
    public Page<GrupocontrataEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (filter.isPresent()) {
            return grupocontrataRepository.findByUsuarioNombreContainingOrPlanesentrenamientoTituloContaining(
                filter.get(), filter.get(), oPageable);
        } else {
            return grupocontrataRepository.findAll(oPageable);
        }
    }

    public Page<GrupocontrataEntity> getPageXUsuario(Pageable oPageable, Optional<String> filter, Optional<Long> id_usuario) {
        if (filter.isPresent()) {
            if (id_usuario.isPresent()) {
                return grupocontrataRepository
                        .findByUsuarioIdAndTituloContainingOrDescipcionContaining(
                                filter.get(), filter.get(), id_usuario.get(),
                                oPageable);
            } else {
                throw new ResourceNotFoundException("Usuario no encontrado");
            }
        } else {
            if (id_usuario.isPresent()) {
                return grupocontrataRepository.findByUsuarioId(id_usuario.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Usuario no encontrado");
            }
        }
    }

    @Override
    public GrupocontrataEntity get(Long id) {
        return grupocontrataRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Grupocontrata con ID " + id + " no encontrado."));
    }

    @Override
    public Long count() {
        return grupocontrataRepository.count();
    }

    @Override
    public Long delete(Long id) {
        GrupocontrataEntity grupocontrataEntity = get(id); // Llama a get para validar existencia
        grupocontrataRepository.delete(grupocontrataEntity);
        return id;
    }

    @Override
    public GrupocontrataEntity create(GrupocontrataEntity grupocontrataEntity) {

        grupocontrataEntity.setUsuario(UsuarioRepository.findById(grupocontrataEntity.getUsuario().getId()).get());
        grupocontrataEntity.setPlanesentrenamiento(PlanesentrenamientoRepository.findById(grupocontrataEntity.getPlanesentrenamiento().getId()).get());
        return grupocontrataRepository.save(grupocontrataEntity);
    }

    @Override
    public GrupocontrataEntity update(GrupocontrataEntity grupocontrataEntity) {
        if (!grupocontrataRepository.existsById(grupocontrataEntity.getId())) {
            throw new ResourceNotFoundException("Grupocontrata con ID " + grupocontrataEntity.getId() + " no encontrado.");
        }

        if (grupocontrataEntity.getUsuario() != null && grupocontrataEntity.getUsuario().getId() != null) {
            UsuarioEntity usuario = UsuarioRepository.findById(grupocontrataEntity.getUsuario().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + grupocontrataEntity.getUsuario().getId() + " no encontrado."));
            grupocontrataEntity.setUsuario(usuario);
        }

        if (grupocontrataEntity.getPlanesentrenamiento() != null && grupocontrataEntity.getPlanesentrenamiento().getId() != null) {
            PlanesentrenamientoEntity plan = PlanesentrenamientoRepository.findById(grupocontrataEntity.getPlanesentrenamiento().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Plan de entrenamiento con ID " + grupocontrataEntity.getPlanesentrenamiento().getId() + " no encontrado."));
            grupocontrataEntity.setPlanesentrenamiento(plan);
        }

        return grupocontrataRepository.save(grupocontrataEntity);
    }


    @Override
    public Long deleteAll() {
        Long count = grupocontrataRepository.count();
        grupocontrataRepository.deleteAll();
        return count;
    }
}
