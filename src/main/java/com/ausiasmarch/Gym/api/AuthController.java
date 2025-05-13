package com.ausiasmarch.Gym.api;

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
        UsuarioEntity nuevo = oUsuarioService.registrarCliente(
            datos.get("nombre"),
            datos.get("apellido1"),
            datos.get("apellido2"),
            datos.get("email"),
            datos.get("password")
        );
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Registro exitoso");
        response.put("usuario", nuevo);
        return ResponseEntity.ok(response); // <-- JSON válido
    } catch (Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}



}
