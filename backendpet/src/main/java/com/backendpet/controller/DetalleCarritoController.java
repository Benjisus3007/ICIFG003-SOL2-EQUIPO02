package com.backendpet.controller;

import com.backendpet.entity.DetalleCarrito;
import com.backendpet.service.DetalleCarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-carrito")
@CrossOrigin(origins = "*")
public class DetalleCarritoController {

    @Autowired
    private DetalleCarritoService detalleCarritoService;

    @GetMapping
    public List<DetalleCarrito> listarTodos() {
        return detalleCarritoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public DetalleCarrito obtenerPorId(@PathVariable Integer id) {
        return detalleCarritoService.obtenerPorId(id);
    }

    @PostMapping
    public DetalleCarrito guardar(@RequestBody DetalleCarrito detalleCarrito) {
        return detalleCarritoService.guardar(detalleCarrito);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        detalleCarritoService.eliminar(id);
    }
}