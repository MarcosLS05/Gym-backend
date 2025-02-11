package com.ausiasmarch.Gym.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ausiasmarch.Gym.entity.TipousuarioEntity;
import com.ausiasmarch.Gym.exception.ResourceNotFoundException;
import com.ausiasmarch.Gym.exception.UnauthorizedAccessException;
import com.ausiasmarch.Gym.repository.TipousuarioRepository;

@Service
public class TipousuarioService implements ServiceInterface<TipousuarioEntity> {

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    @Autowired
    RandomService oRandomService;

    @Autowired
    AuthService oAuthService;  // Inyectar AuthService para verificar permisos

    public Long randomCreate(Long cantidad) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para crear tipos de usuario");
        }
        this.create(new TipousuarioEntity("Administrador"));
        this.create(new TipousuarioEntity("Entrenador Personal"));
        this.create(new TipousuarioEntity("Cliente"));
        return oTipousuarioRepository.count();
    }

    public Page<TipousuarioEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver los tipos de usuario");
        }
        if (filter.isPresent()) {
            return oTipousuarioRepository.findByTituloContaining(filter.get(), oPageable);
        } else {
            return oTipousuarioRepository.findAll(oPageable);
        }
    }

    public TipousuarioEntity get(Long id) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para ver el tipo de usuario");
        }
        return oTipousuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuario no encontrado"));
    }

    public Long count() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para contar los tipos de usuario");
        }
        return oTipousuarioRepository.count();
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oTipousuarioRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para borrar el tipo de usuario");
        }
    }

    public TipousuarioEntity create(TipousuarioEntity oTipousuarioEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para crear el tipo de usuario");
        }
        return oTipousuarioRepository.save(oTipousuarioEntity);
    }

    public TipousuarioEntity update(TipousuarioEntity oTipousuarioEntity) {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para modificar el tipo de usuario");
        }
        TipousuarioEntity oTipousuarioEntityFromDatabase = oTipousuarioRepository.findById(oTipousuarioEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuario no encontrado"));
        if (oTipousuarioEntity.getTitulo() != null) {
            oTipousuarioEntityFromDatabase.setTitulo(oTipousuarioEntity.getTitulo());
        }
        return oTipousuarioRepository.save(oTipousuarioEntityFromDatabase);
    }

    public Long deleteAll() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para borrar todos los tipos de usuario");
        }
        oTipousuarioRepository.deleteAll();
        return this.count();
    }

    public TipousuarioEntity randomSelection() {
        return oTipousuarioRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oTipousuarioRepository.count() - 1)));
    }
}
