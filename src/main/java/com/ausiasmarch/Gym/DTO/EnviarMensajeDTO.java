package com.ausiasmarch.Gym.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EnviarMensajeDTO {

    @NotNull(message = "El ID del emisor es obligatorio")
    private Long emisorId;

    @NotNull(message = "El ID del receptor es obligatorio")
    private Long receptorId;

    @NotBlank(message = "El contenido no puede estar vacío")
    private String contenido;

    // Getters y Setters

    public Long getEmisorId() {
        return emisorId;
    }

    public void setEmisorId(Long emisorId) {
        this.emisorId = emisorId;
    }

    public Long getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(Long receptorId) {
        this.receptorId = receptorId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
