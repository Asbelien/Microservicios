package com.tecsup.controller;

import com.tecsup.dto.AtencionDTO;
import com.tecsup.model.Atencion;
import com.tecsup.services.AtencionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atenciones")
public class AtencionController {

    @Autowired
    private AtencionService service;

    @GetMapping
    public ResponseEntity<List<Atencion>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody AtencionDTO dto) {
        Atencion a = service.registrar(
                dto.getIdCita(),
                dto.getDiagnostico(),
                dto.getTratamiento(),
                dto.getObservaciones()
        );
        return ResponseEntity.status(201).body(a);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtener(id));
    }
}