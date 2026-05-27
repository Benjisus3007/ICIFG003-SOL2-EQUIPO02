package com.backendpet.controller;

import com.backendpet.entity.Mascota;
import com.backendpet.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "http://localhost:4200")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public List<Mascota> listarTodas() {
        return mascotaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Mascota obtenerPorId(@PathVariable Integer id) {
        return mascotaService.obtenerPorId(id);
    }

    @PostMapping
    public Mascota guardar(@RequestBody Mascota mascota) {
        return mascotaService.guardar(mascota);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        mascotaService.eliminar(id);
    }
}