package com.tecsup.services;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Atencion;
import com.tecsup.model.Cita;
import com.tecsup.repository.AtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtencionService {

    @Autowired
    private AtencionRepository repo;

    @Autowired
    private CitaService citaService;

    public List<Atencion> listar() { return repo.findAll(); }

    public Atencion guardar(Atencion a) { return repo.save(a); }

    public Atencion obtener(Integer id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Atención no encontrada con id: " + id));
    }

    public Atencion registrar(Integer idCita, String diagnostico,
                              String tratamiento, String observaciones) {
        Cita cita = citaService.obtener(idCita);
        Atencion a = new Atencion();
        a.setCita(cita);
        a.setDiagnostico(diagnostico);
        a.setTratamiento(tratamiento);
        a.setObservaciones(observaciones);
        a.setFechaRegistro(LocalDateTime.now());
        cita.setEstado("ATENDIDA");
        citaService.guardar(cita);
        return repo.save(a);
    }
}