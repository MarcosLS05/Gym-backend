package com.ausiasmarch.Gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {


    @Autowired
    TipousuarioService oTipousuarioService;


    @Autowired
    UsuarioService oUsuarioService;

    @Autowired
    PlanesentrenamientoService oPlanesentrenamientoService;

    @Autowired
    GrupocontrataService oGrupocontrataService;



    public Long fill() {

        oTipousuarioService.randomCreate(0L);
        oUsuarioService.randomCreate(25L);  
        oPlanesentrenamientoService.randomCreate(10L);
        oGrupocontrataService.randomCreate(10L);
        return 0L;
    }

}

