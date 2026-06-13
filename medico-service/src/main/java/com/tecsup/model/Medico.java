package com.tecsup.model;

import jakarta.persistence.*;

@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedico;

    private String cmp;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private Character estado;

    @ManyToOne
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;

    public Medico() {}

    public Integer getIdMedico() { return idMedico; }
    public void setIdMedico(Integer idMedico) { this.idMedico = idMedico; }

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

    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }
}