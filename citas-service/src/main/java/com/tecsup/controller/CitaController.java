package com.tecsup.controller;

import com.tecsup.dto.CitaDTO;
import com.tecsup.model.Cita;
import com.tecsup.services.CitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService service;

    @GetMapping
    public ResponseEntity<List<Cita>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody CitaDTO dto) {
        Cita c = new Cita();
        c.setFecha(dto.getFecha());
        c.setHora(dto.getHora());
        c.setIdPaciente(dto.getIdPaciente());
        c.setIdMedico(dto.getIdMedico());
        c.setMotivo(dto.getMotivo());
        c.setEstado(dto.getEstado() != null ? dto.getEstado() : "PROGRAMADA");
        return ResponseEntity.status(201).body(service.guardar(c));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(@PathVariable Integer id,
                                              @RequestBody Map<String, String> body) {
        String nuevoEstado = body.get("estado");
        return ResponseEntity.ok(service.actualizarEstado(id, nuevoEstado));
    }

    @DeleteMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable Integer id) {
        service.cancelar(id);
        return ResponseEntity.ok("Cita cancelada correctamente");
    }
}