package com.tecsup.dto;

import jakarta.validation.constraints.NotNull;

public class AtencionDTO {

    @NotNull(message = "El id de la cita es obligatorio")
    private Integer idCita;

    private String diagnostico;
    private String tratamiento;
    private String observaciones;

    public Integer getIdCita() { return idCita; }
    public void setIdCita(Integer idCita) { this.idCita = idCita; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}