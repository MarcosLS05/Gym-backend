package com.ausiasmarch.Gym.DTO;

public class CreateGcontrataClienteDto {

    private Long usuarioId;
    private Long planId;

    public CreateGcontrataClienteDto() {}

    public CreateGcontrataClienteDto(Long usuarioId, Long planId) {
        this.usuarioId = usuarioId;
        this.planId = planId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }
}
