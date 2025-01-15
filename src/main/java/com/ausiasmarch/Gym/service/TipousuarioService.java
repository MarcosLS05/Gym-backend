package com.ausiasmarch.Gym.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ausiasmarch.Gym.entity.TipousuarioEntity;
import com.ausiasmarch.Gym.exception.ResourceNotFoundException;
import com.ausiasmarch.Gym.repository.TipousuarioRepository;

@Service
public class TipousuarioService implements ServiceInterface<TipousuarioEntity> {
    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    @Autowired
    RandomService oRandomService;

    public Long randomCreate(Long cantidad) {
        this.create(new TipousuarioEntity("Administrador"));
        this.create(new TipousuarioEntity("Entrenador Personal"));
        this.create(new TipousuarioEntity("Cliente"));
        return oTipousuarioRepository.count();
    }

    public Page<TipousuarioEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (filter.isPresent()) {
            return oTipousuarioRepository
                    .findByTituloContaining(filter.get(), oPageable);
        } else {
            return oTipousuarioRepository.findAll(oPageable);
        }
    }

    public TipousuarioEntity get(Long id) {
        return oTipousuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuario no encontrado"));
        // return oTipousuarioRepository.findById(id).get();
    }

    public Long count() {
        return oTipousuarioRepository.count();
    }

    public Long delete(Long id) {
        oTipousuarioRepository.deleteById(id);
        return 1L;
    }

    public TipousuarioEntity create(TipousuarioEntity oTipousuarioEntity) {
        return oTipousuarioRepository.save(oTipousuarioEntity);
    }

    public TipousuarioEntity update(TipousuarioEntity oTipousuarioEntity) {
        TipousuarioEntity oTipousuarioEntityFromDatabase = oTipousuarioRepository.findById(oTipousuarioEntity.getId())
                .get();
        if (oTipousuarioEntity.getTitulo() != null) {
            oTipousuarioEntityFromDatabase.setTitulo(oTipousuarioEntity.getTitulo());
        }
        return oTipousuarioRepository.save(oTipousuarioEntityFromDatabase);
    }

    public Long deleteAll() {
        oTipousuarioRepository.deleteAll();
        return this.count();
    }

    public TipousuarioEntity randomSelection() {
        return oTipousuarioRepository.findAll()
                .get(oRandomService.getRandomInt(0, (int) (oTipousuarioRepository.count() - 1)));
    }
}
