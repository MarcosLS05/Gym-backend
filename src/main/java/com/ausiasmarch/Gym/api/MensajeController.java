package com.ausiasmarch.Gym.api;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ausiasmarch.Gym.DTO.EnviarMensajeDTO;
import com.ausiasmarch.Gym.entity.MensajeEntity;
import com.ausiasmarch.Gym.entity.UsuarioEntity;
import com.ausiasmarch.Gym.exception.ResourceNotFoundException;
import com.ausiasmarch.Gym.exception.UnauthorizedAccessException;
import com.ausiasmarch.Gym.repository.MensajeRepository;
import com.ausiasmarch.Gym.repository.UsuarioRepository;
import com.ausiasmarch.Gym.service.AuthService;
import com.ausiasmarch.Gym.service.MensajeService;

import jakarta.validation.Valid;


@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService oMensajeService;

    @Autowired
    private UsuarioRepository oUsuarioRepository;

    @Autowired
    MensajeRepository oMensajeRepository;

    @Autowired
    private AuthService oAuthService;

    
    @GetMapping("/conversacion")
    public List<MensajeEntity> getConversacion(
            @RequestParam Long usuario1Id,
            @RequestParam Long usuario2Id) {
        return oMensajeService.getConversacion(usuario1Id, usuario2Id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeEntity> get(@PathVariable Long id) {
        return new ResponseEntity<MensajeEntity>(oMensajeService.get(id), HttpStatus.OK);
    }

    // Obtener lista de usuarios conversados
    @GetMapping("/usuarios")
    public List<UsuarioEntity> getUsuariosConversados(@RequestParam Long userId) {
        return oMensajeService.getUsuariosConversados(userId);
    }

@PostMapping("/new")
public ResponseEntity<MensajeEntity> createMensaje(@RequestBody @Valid EnviarMensajeDTO dto) {
    try {
        if (!oAuthService.isAdmin()) {
            throw new UnauthorizedAccessException("No tienes permisos para crear el mensaje");
        }

        MensajeEntity mensaje = new MensajeEntity();
        mensaje.setContenido(dto.getContenido());
        mensaje.setFechaEnvio(new Date());

        // Establecer receptor
        UsuarioEntity receptor = oUsuarioRepository.findById(dto.getReceptorId())
                .orElseThrow(() -> new ResourceNotFoundException("Receptor no encontrado"));
        mensaje.setReceptor(receptor);

        // Establecer emisor
        UsuarioEntity emisor = oUsuarioRepository.findById(dto.getEmisorId())
                .orElseThrow(() -> new ResourceNotFoundException("Emisor no encontrado"));
        mensaje.setEmisor(emisor);

        // Guardar mensaje
        MensajeEntity saved = oMensajeRepository.save(mensaje);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);

    } catch (UnauthorizedAccessException e) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@PutMapping("")
public ResponseEntity<MensajeEntity> update(@RequestBody MensajeEntity oMensajeEntity) {
    return ResponseEntity.ok(oMensajeService.update(oMensajeEntity));
}

    @GetMapping("/recibidos/{receptorId}")
public ResponseEntity<List<MensajeEntity>> getMensajesRecibidos(@PathVariable Long receptorId) {
    List<MensajeEntity> mensajes = oMensajeService.obtenerMensajesRecibidos(receptorId);
    return ResponseEntity.ok(mensajes);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oMensajeService.delete(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<MensajeEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<MensajeEntity>>(oMensajeService.getPage(oPageable, filter), HttpStatus.OK);
    }
}
