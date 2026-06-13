package com.tecsup.controller;

import com.tecsup.dto.EspecialidadDTO;
import com.tecsup.model.Especialidad;
import com.tecsup.services.EspecialidadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadService service;

    @GetMapping
    public ResponseEntity<List<Especialidad>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody EspecialidadDTO dto) {
        Especialidad e = new Especialidad();
        e.setNombre(dto.getNombre());
        e.setDescripcion(dto.getDescripcion());
        e.setEstado(dto.getEstado() != null ? dto.getEstado() : 'A');
        return ResponseEntity.status(201).body(service.guardar(e));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id,
                                        @Valid @RequestBody EspecialidadDTO dto) {
        Especialidad e = service.obtener(id);
        e.setNombre(dto.getNombre());
        e.setDescripcion(dto.getDescripcion());
        e.setEstado(dto.getEstado());
        return ResponseEntity.ok(service.guardar(e));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok("Especialidad eliminada correctamente");
    }
}