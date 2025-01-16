package com.ausiasmarch.Gym.api;

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

import com.ausiasmarch.Gym.entity.GrupocontrataEntity;
import com.ausiasmarch.Gym.entity.PlanesentrenamientoEntity;
import com.ausiasmarch.Gym.entity.UsuarioEntity;
import com.ausiasmarch.Gym.service.PlanesentrenamientoService;


@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/planesentrenamiento")
public class Planesentrenamiento {
    
    @Autowired
    PlanesentrenamientoService oPlanesentrenamientoService;

    @GetMapping("")
    public ResponseEntity<Page<PlanesentrenamientoEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<PlanesentrenamientoEntity>>(oPlanesentrenamientoService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanesentrenamientoEntity> get(@PathVariable Long id) {
        return new ResponseEntity<PlanesentrenamientoEntity>(oPlanesentrenamientoService.get(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oPlanesentrenamientoService.delete(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<PlanesentrenamientoEntity> create(@RequestBody PlanesentrenamientoEntity oPlanesentrenamientoEntity) {
        return new ResponseEntity<PlanesentrenamientoEntity>(oPlanesentrenamientoService.create(oPlanesentrenamientoEntity), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PlanesentrenamientoEntity> update(@RequestBody PlanesentrenamientoEntity oPlanesentrenamientoEntity) {
        return new ResponseEntity<PlanesentrenamientoEntity>(oPlanesentrenamientoService.update(oPlanesentrenamientoEntity), HttpStatus.OK);
    }
}
