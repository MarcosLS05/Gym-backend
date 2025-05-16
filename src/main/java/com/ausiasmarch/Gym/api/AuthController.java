package com.ausiasmarch.Gym.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ausiasmarch.Gym.bean.LogindataBean;
import com.ausiasmarch.Gym.entity.UsuarioEntity;
import com.ausiasmarch.Gym.service.AuthService;
import com.ausiasmarch.Gym.service.UsuarioService;

import jakarta.annotation.security.PermitAll;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService oAuthService;

    @Autowired
    UsuarioService oUsuarioService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LogindataBean oLogindataBean) {
        if (oAuthService.checkLogin(oLogindataBean)) {
            return ResponseEntity.ok("\"" + oAuthService.getToken(oLogindataBean.getEmail()) + "\"");
        } else {
            return ResponseEntity.status(401).body("\"" + "Error de autenticación" + "\"");
        }
    }

@PostMapping("/register")
@PermitAll
public ResponseEntity<?> register(@RequestBody Map<String, String> datos) {
    try {
        // Convertir codigo_postal a Long
        Long codigoPostal = Long.valueOf(datos.get("codigo_postal"));

        // Convertir fecha_nacimiento a java.util.Date y luego a java.sql.Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento = dateFormat.parse(datos.get("fecha_nacimiento"));
        java.sql.Date fechaNacimientoSql = new java.sql.Date(fechaNacimiento.getTime());
        boolean esEntrenador = Boolean.parseBoolean(datos.get("esEntrenador"));


        // Llamar al servicio con los parámetros correctos
        UsuarioEntity nuevo = oUsuarioService.registrarCliente(
            datos.get("nombre"),
            datos.get("apellido1"),
            datos.get("apellido2"),
            datos.get("email"),
            datos.get("password"),
            datos.get("telefono"),
            codigoPostal,
            datos.get("direccion"),
            datos.get("provincia"),
            datos.get("dni"),
            fechaNacimientoSql,
            esEntrenador
        );

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Registro exitoso");
        response.put("usuario", nuevo);
        return ResponseEntity.ok(response);

    } catch (Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}



}
