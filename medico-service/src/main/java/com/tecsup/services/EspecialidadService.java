package com.tecsup.services;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Especialidad;
import com.tecsup.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository repo;

    public List<Especialidad> listar() { return repo.findAll(); }

    public Especialidad guardar(Especialidad e) { return repo.save(e); }

    public Especialidad obtener(Integer id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Especialidad no encontrada con id: " + id));
    }

    public void eliminar(Integer id) {
        Especialidad e = obtener(id);
        repo.delete(e);
    }
}