package com.ausiasmarch.Gym.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import com.ausiasmarch.Gym.bean.LogindataBean;
import com.ausiasmarch.Gym.entity.UsuarioEntity;
import com.ausiasmarch.Gym.exception.UnauthorizedAccessException;
import com.ausiasmarch.Gym.repository.UsuarioRepository;;
@Service
public class AuthService {

    @Autowired
    JWTService JWTHelper;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    public boolean checkLogin(LogindataBean oLogindataBean) {
        if (oUsuarioRepository.findByEmailAndPassword(oLogindataBean.getEmail(), oLogindataBean.getPassword())
                .isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    private Map<String, String> getClaims(String email) {
        Map<String, String> claims = new HashMap<>();
        claims.put("email", email);
        return claims;
    };

    public String getToken(String email) {
        return JWTHelper.generateToken(getClaims(email));
    }

    public UsuarioEntity getUsuarioFromToken() {
        if (oHttpServletRequest.getAttribute("email") == null) {
            throw new UnauthorizedAccessException("No hay usuario en la sesión");
        } else {
            String email = oHttpServletRequest.getAttribute("email").toString();
            return oUsuarioRepository.findByEmail(email).get();
        }                
    }

    public boolean isSessionActive() {
        return oHttpServletRequest.getAttribute("email") != null;
    }

    public boolean isAdmin() {
        return this.getUsuarioFromToken().getTipousuario().getId() == 1L;
    }

    public boolean isEntrenadorPersonal() {
        return this.getUsuarioFromToken().getTipousuario().getId() == 2L;
    }

    public boolean isCliente() {
        return this.getUsuarioFromToken().getTipousuario().getId() == 3L;
    }

    public boolean isAdminOrContable() {
        return this.isAdmin() || this.isEntrenadorPersonal();
    }

    public boolean isEntrenadorPersonalWithItsOwnData(Long id) {
        UsuarioEntity oUsuarioEntity = this.getUsuarioFromToken();
        return this.isEntrenadorPersonal() && oUsuarioEntity.getId() == id;
    }

    public boolean isClienteWithItsOwnData(Long id) {
        UsuarioEntity oUsuarioEntity = this.getUsuarioFromToken();
        return this.isCliente() && oUsuarioEntity.getId() == id;
    }

}
