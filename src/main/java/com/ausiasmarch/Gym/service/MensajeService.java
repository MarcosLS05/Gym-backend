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
import com.ausiasmarch.Gym.exception.ResourceNotFoundException;
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

    
public MensajeEntity create(MensajeEntity oMensajeEntity) {
    if (oAuthService.isAdmin()) {
        // Buscar el emisor por ID
        UsuarioEntity emisor = oUsuarioRepository.findById(oMensajeEntity.getEmisor().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Emisor no encontrado"));

        // Buscar el receptor por ID
        UsuarioEntity receptor = oUsuarioRepository.findById(oMensajeEntity.getReceptor().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Receptor no encontrado"));

        oMensajeEntity.setEmisor(emisor);
        oMensajeEntity.setReceptor(receptor);

        return oMensajeRepository.save(oMensajeEntity);
    } else {
        throw new UnauthorizedAccessException("No tienes permisos para crear el mensaje");
    }
}


public List<MensajeEntity> obtenerMensajesRecibidos(Long receptorId) {
    return oMensajeRepository.findMensajesRecibidos(receptorId);
}
  

public MensajeEntity update(MensajeEntity oMensajeEntity) {

    if (!oAuthService.isAdmin()) {
        throw new UnauthorizedAccessException("No tienes permiso para modificar el mensaje.");
    }

    // Comprobar si el mensaje existe
    if (oMensajeEntity.getId() == null || !oMensajeRepository.existsById(oMensajeEntity.getId())) {
        throw new ResourceNotFoundException("Mensaje con ID " + oMensajeEntity.getId() + " no encontrado.");
    }

    // Validar contenido
    if (oMensajeEntity.getContenido() == null || oMensajeEntity.getContenido().trim().isEmpty()) {
        throw new IllegalArgumentException("El contenido del mensaje no puede estar vacío.");
    }

    // Validar emisor
    if (oMensajeEntity.getEmisor() == null || oMensajeEntity.getEmisor().getId() == null) {
        throw new IllegalArgumentException("El mensaje debe tener un emisor válido.");
    }

    UsuarioEntity emisor = oUsuarioRepository.findById(oMensajeEntity.getEmisor().getId())
        .orElseThrow(() -> new ResourceNotFoundException("Emisor con ID " + oMensajeEntity.getEmisor().getId() + " no encontrado."));

    // Validar receptor
    if (oMensajeEntity.getUsuario() == null || oMensajeEntity.getUsuario().getId() == null) {
        throw new IllegalArgumentException("El mensaje debe tener un receptor válido.");
    }

    UsuarioEntity receptor = oUsuarioRepository.findById(oMensajeEntity.getUsuario().getId())
        .orElseThrow(() -> new ResourceNotFoundException("Receptor con ID " + oMensajeEntity.getUsuario().getId() + " no encontrado."));

    // Asignar usuarios verificados
    oMensajeEntity.setEmisor(emisor);
    oMensajeEntity.setUsuario(receptor);

    // Guardar mensaje actualizado
    return oMensajeRepository.save(oMensajeEntity);
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
