package com.tecsup.controller;

import com.tecsup.dto.PacienteDTO;
import com.tecsup.model.Paciente;
import com.tecsup.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody PacienteDTO dto) {
        Paciente p = new Paciente();
        p.setDni(dto.getDni());
        p.setNombres(dto.getNombres());
        p.setApellidos(dto.getApellidos());
        p.setFechaNacimiento(dto.getFechaNacimiento() != null ?
                LocalDate.parse(dto.getFechaNacimiento()) : null);
        p.setSexo(dto.getSexo());
        p.setTelefono(dto.getTelefono());
        p.setDireccion(dto.getDireccion());
        p.setCorreo(dto.getCorreo());
        p.setEstado(dto.getEstado() != null ? dto.getEstado() : "A");
        return ResponseEntity.status(201).body(service.guardar(p));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody PacienteDTO dto) {
        Paciente existente = service.obtener(id);
        existente.setDni(dto.getDni());
        existente.setNombres(dto.getNombres());
        existente.setApellidos(dto.getApellidos());
        existente.setFechaNacimiento(dto.getFechaNacimiento() != null ?
                LocalDate.parse(dto.getFechaNacimiento()) : null);
        existente.setSexo(dto.getSexo());
        existente.setTelefono(dto.getTelefono());
        existente.setDireccion(dto.getDireccion());
        existente.setCorreo(dto.getCorreo());
        existente.setEstado(dto.getEstado());
        return ResponseEntity.ok(service.guardar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Paciente eliminado correctamente");
    }
}