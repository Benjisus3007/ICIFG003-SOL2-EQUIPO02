package com.backendpet.controller;

import com.backendpet.entity.Producto;
import com.backendpet.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listarTodos() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/categoria/{id}")
    public List<Producto> listarPorCategoria(@PathVariable Integer id) {
        return productoService.obtenerPorCategoria(id);
    }
}