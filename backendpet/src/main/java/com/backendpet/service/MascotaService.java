package com.backendpet.service;

import com.backendpet.entity.Mascota;
import com.backendpet.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> obtenerTodos() {
        return mascotaRepository.findAll();
    }

    public Mascota obtenerPorId(Integer id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    public Mascota guardar(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public void eliminar(Integer id) {
        mascotaRepository.deleteById(id);
    }
}