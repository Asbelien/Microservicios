package com.tecsup.controller;

import com.tecsup.dto.MedicoDTO;
import com.tecsup.model.Especialidad;
import com.tecsup.model.Medico;
import com.tecsup.services.EspecialidadService;
import com.tecsup.services.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public ResponseEntity<List<Medico>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody MedicoDTO dto) {
        Especialidad esp = especialidadService.obtener(dto.getIdEspecialidad());
        Medico m = new Medico();
        m.setCmp(dto.getCmp());
        m.setNombres(dto.getNombres());
        m.setApellidos(dto.getApellidos());
        m.setTelefono(dto.getTelefono());
        m.setCorreo(dto.getCorreo());
        m.setEstado(dto.getEstado() != null ? dto.getEstado() : 'A');
        m.setEspecialidad(esp);
        return ResponseEntity.status(201).body(service.guardar(m));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id,
                                        @Valid @RequestBody MedicoDTO dto) {
        Medico m = service.obtener(id);
        Especialidad esp = especialidadService.obtener(dto.getIdEspecialidad());
        m.setCmp(dto.getCmp());
        m.setNombres(dto.getNombres());
        m.setApellidos(dto.getApellidos());
        m.setTelefono(dto.getTelefono());
        m.setCorreo(dto.getCorreo());
        m.setEstado(dto.getEstado());
        m.setEspecialidad(esp);
        return ResponseEntity.ok(service.guardar(m));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok("Médico eliminado correctamente");
    }
}