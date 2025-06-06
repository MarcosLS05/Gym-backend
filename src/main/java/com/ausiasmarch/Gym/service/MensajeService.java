package com.ausiasmarch.Gym.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ausiasmarch.Gym.DTO.EnviarMensajeDTO;
import com.ausiasmarch.Gym.entity.MensajeEntity;
import com.ausiasmarch.Gym.entity.UsuarioEntity;
import com.ausiasmarch.Gym.exception.UnauthorizedAccessException;
import com.ausiasmarch.Gym.repository.MensajeRepository;
import com.ausiasmarch.Gym.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;


@Service
public class MensajeService {

    @Autowired
    private MensajeRepository oMensajeRepository;

    @Autowired
    private UsuarioRepository oUsuarioRepository;

    @Autowired
    private AuthService oAuthService;

    public List<MensajeEntity> getConversacion(Long usuario1Id, Long usuario2Id) {
        return oMensajeRepository.findConversacion(usuario1Id, usuario2Id);
    }

    public List<UsuarioEntity> getUsuariosConversados(Long userId) {
        return oMensajeRepository.findUsuariosConversados(userId);
    }

public MensajeEntity enviarMensaje(EnviarMensajeDTO dto) {
    UsuarioEntity emisor = oUsuarioRepository.findById(dto.getEmisorId())
            .orElseThrow(() -> new RuntimeException("Emisor no encontrado"));
    UsuarioEntity receptor = oUsuarioRepository.findById(dto.getReceptorId())
            .orElseThrow(() -> new RuntimeException("Receptor no encontrado"));

    MensajeEntity mensaje = new MensajeEntity();
    mensaje.setEmisor(emisor);
    mensaje.setReceptor(receptor);
    mensaje.setContenido(dto.getContenido());
    mensaje.setFechaEnvio(LocalDateTime.now());
    mensaje.setLeido(false);

    return oMensajeRepository.save(mensaje);
}

    public void marcarComoLeido(Long mensajeId) {
        oMensajeRepository.findById(mensajeId).ifPresent(m -> {
            m.setLeido(true);
            oMensajeRepository.save(m);
        });
    }

        public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oMensajeRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para borrar el mensajes");
        }
    }

    public Page<MensajeEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (oAuthService.isAdmin()) {
            if (filter.isPresent()) {
                return oMensajeRepository
                        .findByContenidoContaining(
                                filter.get(), oPageable);
                                
            } else {
                return oMensajeRepository.findAll(oPageable);
            }
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para ver los mensajes");
        }

    }

    public MensajeEntity update(MensajeEntity oMensajeEntity) {
        if (oAuthService.isEntrenadorPersonalWithItsOwnData(oMensajeEntity.getId()) || oAuthService.isAdmin()
                || oAuthService.isClienteWithItsOwnData(oMensajeEntity.getId())) {
            MensajeEntity oMensajeEntityFromDatabase = oMensajeRepository.findById(oMensajeEntity.getId()).get();
            if (oMensajeEntity.getContenido() != null) {
                oMensajeEntityFromDatabase.setContenido(oMensajeEntity.getContenido());
            }
            return oMensajeRepository.save(oMensajeEntityFromDatabase);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para modificar el usuario");
        }
    }

    public MensajeEntity get(Long id) {
        if (oAuthService.isEntrenadorPersonalWithItsOwnData(id) || oAuthService.isAdmin()
                || oAuthService.isClienteWithItsOwnData(id)) {
            Optional<MensajeEntity> mensaje = oMensajeRepository.findById(id);
            if (mensaje.isPresent()) {
                return mensaje.get();
            } else {
                throw new EntityNotFoundException("Mensaje no encontrado con ID: " + id);
            }
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para ver el mensaje");
        }
    }
}
