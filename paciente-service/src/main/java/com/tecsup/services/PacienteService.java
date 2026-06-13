package com.tecsup.services;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Paciente;
import com.tecsup.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repo;

    public List<Paciente> listar() {
        return repo.findAll();
    }

    public Paciente guardar(Paciente p) {
        return repo.save(p);
    }

    public Paciente obtener(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Paciente no encontrado con id: " + id));
    }

    public void eliminar(Long id) {
        Paciente p = repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Paciente no encontrado con id: " + id));
        repo.delete(p);
    }
}