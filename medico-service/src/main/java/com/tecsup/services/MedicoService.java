package com.tecsup.services;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Medico;
import com.tecsup.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repo;

    public List<Medico> listar() { return repo.findAll(); }

    public Medico guardar(Medico m) { return repo.save(m); }

    public Medico obtener(Integer id) {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Médico no encontrado con id: " + id));
    }

    public void eliminar(Integer id) {
        Medico m = obtener(id);
        repo.delete(m);
    }
}