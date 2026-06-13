package com.tecsup.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicoDTO {

    private String cmp;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    private String telefono;
    private String correo;
    private Character estado;

    @NotNull(message = "La especialidad es obligatoria")
    private Integer idEspecialidad;

    public String getCmp() { return cmp; }
    public void setCmp(String cmp) { this.cmp = cmp; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public Character getEstado() { return estado; }
    public void setEstado(Character estado) { this.estado = estado; }

    public Integer getIdEspecialidad() { return idEspecialidad; }
    public void setIdEspecialidad(Integer idEspecialidad) { this.idEspecialidad = idEspecialidad; }
}