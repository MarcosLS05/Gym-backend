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

import com.ausiasmarch.Gym.entity.TipousuarioEntity;
import com.ausiasmarch.Gym.service.TipousuarioService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/tipousuario")
public class Tipousuario {
    
    @Autowired
    TipousuarioService oTipousuarioService;

    @GetMapping("")
    public ResponseEntity<Page<TipousuarioEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<TipousuarioEntity>>(oTipousuarioService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipousuarioEntity> get(@PathVariable Long id) {
        return new ResponseEntity<TipousuarioEntity>(oTipousuarioService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oTipousuarioService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/new")
    public ResponseEntity<TipousuarioEntity> create(@RequestBody TipousuarioEntity oTipousuarioEntity) {
        return new ResponseEntity<TipousuarioEntity>(oTipousuarioService.create(oTipousuarioEntity), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<TipousuarioEntity> update(@RequestBody TipousuarioEntity oTipousuarioEntity) {
        return new ResponseEntity<TipousuarioEntity>(oTipousuarioService.update(oTipousuarioEntity), HttpStatus.OK);
    }

}
