package com.ausiasmarch.Gym.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ausiasmarch.Gym.DTO.EnviarMensajeDTO;
import com.ausiasmarch.Gym.entity.MensajeEntity;
import com.ausiasmarch.Gym.entity.UsuarioEntity;
import com.ausiasmarch.Gym.service.MensajeService;

import jakarta.validation.Valid;


@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService oMensajeService;

    
    @GetMapping("/conversacion")
    public List<MensajeEntity> getConversacion(
            @RequestParam Long usuario1Id,
            @RequestParam Long usuario2Id) {
        return oMensajeService.getConversacion(usuario1Id, usuario2Id);
    }

    // Obtener lista de usuarios conversados
    @GetMapping("/usuarios")
    public List<UsuarioEntity> getUsuariosConversados(@RequestParam Long userId) {
        return oMensajeService.getUsuariosConversados(userId);
    }

    // Enviar mensaje
    @PostMapping("/enviar")
    public ResponseEntity<MensajeEntity> enviarMensaje(@Valid @RequestBody EnviarMensajeDTO dto) {
        MensajeEntity mensaje = oMensajeService.enviarMensaje(dto);
        return ResponseEntity.ok(mensaje);
    }
    // Marcar mensaje como leído
    @PostMapping("/leer/{mensajeId}")
    public void marcarComoLeido(@PathVariable Long mensajeId) {
        oMensajeService.marcarComoLeido(mensajeId);
    }
}
