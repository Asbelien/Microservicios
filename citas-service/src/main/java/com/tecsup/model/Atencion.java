package com.tecsup.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "atencion")
public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAtencion;

    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private LocalDateTime fechaRegistro;

    @OneToOne
    @JoinColumn(name = "id_cita")
    private Cita cita;

    public Atencion() {}

    public Integer getIdAtencion() { return idAtencion; }
    public void setIdAtencion(Integer idAtencion) { this.idAtencion = idAtencion; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public Cita getCita() { return cita; }
    public void setCita(Cita cita) { this.cita = cita; }
}