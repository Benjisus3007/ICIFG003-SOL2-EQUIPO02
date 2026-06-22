package com.backendpet.controller;

import com.backendpet.dto.CompraResponseDTO;
import com.backendpet.dto.CrearCompraRequest;
import com.backendpet.entity.Compra;
import com.backendpet.service.CompraService;
import com.backendpet.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "http://localhost:4200")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private CompraRepository compraRepository;

    @PostMapping("/confirmar")
    public CompraResponseDTO confirmarCompra(@RequestBody CrearCompraRequest request) {
        return compraService.crearCompra(request);
    }

    @GetMapping
    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }
}