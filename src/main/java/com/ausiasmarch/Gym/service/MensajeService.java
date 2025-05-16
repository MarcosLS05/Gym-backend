package com.ausiasmarch.Gym.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ausiasmarch.Gym.DTO.EnviarMensajeDTO;
import com.ausiasmarch.Gym.entity.MensajeEntity;
import com.ausiasmarch.Gym.entity.UsuarioEntity;
import com.ausiasmarch.Gym.repository.MensajeRepository;
import com.ausiasmarch.Gym.repository.UsuarioRepository;
import java.time.LocalDateTime;


@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private UsuarioRepository oUsuarioRepository;

    public List<MensajeEntity> getConversacion(Long usuario1Id, Long usuario2Id) {
        return mensajeRepository.findConversacion(usuario1Id, usuario2Id);
    }

    public List<UsuarioEntity> getUsuariosConversados(Long userId) {
        return mensajeRepository.findUsuariosConversados(userId);
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

    return mensajeRepository.save(mensaje);
}

    public void marcarComoLeido(Long mensajeId) {
        mensajeRepository.findById(mensajeId).ifPresent(m -> {
            m.setLeido(true);
            mensajeRepository.save(m);
        });
    }
}
