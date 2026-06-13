package com.tecsup.dto;

import jakarta.validation.constraints.NotBlank;

public class EspecialidadDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;
    private Character estado;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Character getEstado() { return estado; }
    public void setEstado(Character estado) { this.estado = estado; }
}