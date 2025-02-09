package com.ausiasmarch.Gym.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ausiasmarch.Gym.entity.UsuarioEntity;
import com.ausiasmarch.Gym.exception.ResourceNotFoundException;
import com.ausiasmarch.Gym.exception.UnauthorizedAccessException;
import com.ausiasmarch.Gym.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class UsuarioService implements ServiceInterface<UsuarioEntity> {

    HttpServletRequest oHttpServletRequest;

    @Autowired
    AuthService oAuthService;
      
    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    TipousuarioService oTipousuarioService;

    @Autowired
    RandomService oRandomService;

    private String[] arrNombres = {"Pepe", "Laura", "Ignacio", "Maria", "Lorenzo", "Carmen", "Rosa", "Paco", "Luis",
        "Ana", "Rafa", "Manolo", "Lucia", "Marta", "Sara", "Rocio"};

    private String[] arrApellidos = {"Sancho", "Gomez", "Pérez", "Rodriguez", "Garcia", "Fernandez", "Lopez",
        "Martinez", "Sanchez", "Gonzalez", "Gimenez", "Feliu", "Gonzalez", "Hermoso", "Vidal", "Escriche", "Moreno"};


    public Long randomCreate(Long cantidad) {
        for (int i = 0; i < cantidad; i++) {
            UsuarioEntity oUsuarioEntity = new UsuarioEntity();
            oUsuarioEntity.setNombre(arrNombres[oRandomService.getRandomInt(0, arrNombres.length - 1)]);
            oUsuarioEntity.setApellido1(arrApellidos[oRandomService.getRandomInt(0, arrApellidos.length - 1)]);
            oUsuarioEntity.setApellido2(arrApellidos[oRandomService.getRandomInt(0, arrApellidos.length - 1)]);
            oUsuarioEntity.setEmail("email" + oUsuarioEntity.getNombre() + oRandomService.getRandomInt(999, 9999) + "@gmail.com");
            oUsuarioEntity.setTipousuario(oTipousuarioService.randomSelection());
            oUsuarioRepository.save(oUsuarioEntity);
        }
        return oUsuarioRepository.count();
    }

    public UsuarioEntity getByEmail(String email) {
        UsuarioEntity oUsuarioEntity = oUsuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con email " + email + " no existe"));
        if (oAuthService.isEntrenadorPersonalWithItsOwnData(oUsuarioEntity.getId()) || oAuthService.isAdmin()
                || oAuthService.isClienteWithItsOwnData(oUsuarioEntity.getId())) {
            return oUsuarioEntity;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para ver el usuario");
        }
    }

    public UsuarioEntity get(Long id) {
        if (oAuthService.isEntrenadorPersonalWithItsOwnData(id) || oAuthService.isAdmin()
                || oAuthService.isClienteWithItsOwnData(id)) {
            Optional<UsuarioEntity> usuario = oUsuarioRepository.findById(id);
            if (usuario.isPresent()) {
                return usuario.get();
            } else {
                throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
            }
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para ver el usuario");
        }
    }

    public Page<UsuarioEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (oAuthService.isAdmin()) {
            if (filter.isPresent()) {
                return oUsuarioRepository
                        .findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContaining(
                                filter.get(), filter.get(), filter.get(), filter.get(),
                                oPageable);
            } else {
                return oUsuarioRepository.findAll(oPageable);
            }
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para ver los usuarios");
        }

    }

    public Page<UsuarioEntity> getPageXTipoUsuario(Pageable oPageable, Optional<String> filter, Optional<Long> id_tipousuario) {
        if (filter.isPresent()) {
            if (id_tipousuario.isPresent()) {
                return oUsuarioRepository
                        .findByTipousuarioIdAndTituloContaining(
                            id_tipousuario.get(), filter.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Tipousuario no encontrado");
            }            
        } else {
            if (id_tipousuario.isPresent()) {
                return oUsuarioRepository.findByTipousuarioId(id_tipousuario.get(), oPageable);
            } else {
                throw new ResourceNotFoundException("Tipousuario no encontrado");
            }
        }
    }

    public Long count() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para contar los usuarios");
        } else {
            return oUsuarioRepository.count();
        }
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oUsuarioRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para borrar el usuario");
        }
    }

    public UsuarioEntity create(UsuarioEntity oUsuarioEntity) {
        if (oAuthService.isAdmin()) {
            return oUsuarioRepository.save(oUsuarioEntity);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para crear el usuario");
        }
    }

    public UsuarioEntity update(UsuarioEntity oUsuarioEntity) {
        if (oAuthService.isEntrenadorPersonalWithItsOwnData(oUsuarioEntity.getId()) || oAuthService.isAdmin()
                || oAuthService.isClienteWithItsOwnData(oUsuarioEntity.getId())) {
            UsuarioEntity oUsuarioEntityFromDatabase = oUsuarioRepository.findById(oUsuarioEntity.getId()).get();
            if (oUsuarioEntity.getNombre() != null) {
                oUsuarioEntityFromDatabase.setNombre(oUsuarioEntity.getNombre());
            }
            if (oUsuarioEntity.getApellido1() != null) {
                oUsuarioEntityFromDatabase.setApellido1(oUsuarioEntity.getApellido1());
            }
            if (oUsuarioEntity.getApellido2() != null) {
                oUsuarioEntityFromDatabase.setApellido2(oUsuarioEntity.getApellido2());
            }
            if (oUsuarioEntity.getEmail() != null) {
                oUsuarioEntityFromDatabase.setEmail(oUsuarioEntity.getEmail());
            }
            return oUsuarioRepository.save(oUsuarioEntityFromDatabase);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para modificar el usuario");
        }
    }

    public Long deleteAll() {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para borrar todos los usuarios");
        } else {
            oUsuarioRepository.deleteAll();
            return this.count();
        }
    }

    public UsuarioEntity randomSelection() {
        return oUsuarioRepository.findById((long) oRandomService.getRandomInt(1, (int) (long) this.count())).get();
    }
}