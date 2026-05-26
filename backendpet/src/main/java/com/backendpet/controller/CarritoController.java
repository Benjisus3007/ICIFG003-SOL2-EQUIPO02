package com.backendpet.controller;

import com.backendpet.entity.Carrito;
import com.backendpet.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public List<Carrito> listarTodos() {
        return carritoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Carrito obtenerPorId(@PathVariable Integer id) {
        return carritoService.obtenerPorId(id);
    }

    @PostMapping
    public Carrito guardar(@RequestBody Carrito carrito) {
        return carritoService.guardar(carrito);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        carritoService.eliminar(id);
    }
}