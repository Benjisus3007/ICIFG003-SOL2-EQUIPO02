package com.backendpet.controller;

import com.backendpet.entity.CategoriaProducto;
import com.backendpet.service.CategoriaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController 
@RequestMapping("/api/categorias") 
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaProductoController {

    @Autowired
    private CategoriaProductoService categoriaService;

    
    @GetMapping
    public List<CategoriaProducto> listarTodas() {
        return categoriaService.obtenerTodas();
    }
}
