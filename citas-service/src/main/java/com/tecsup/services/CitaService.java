package com.tecsup.services;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Cita;
import com.tecsup.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository repo;

    public List<Cita> listar() { return repo.findAll(); }

    public Cita guardar(Cita c) { return repo.save(c); }

    public Cita obtener(Integer id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cita no encontrada con id: " + id));
    }

    public Cita actualizarEstado(Integer id, String nuevoEstado) {
        Cita cita = obtener(id);
        cita.setEstado(nuevoEstado);
        return repo.save(cita);
    }

    public void cancelar(Integer id) {
        Cita cita = obtener(id);
        cita.setEstado("CANCELADA");
        repo.save(cita);
    }
}