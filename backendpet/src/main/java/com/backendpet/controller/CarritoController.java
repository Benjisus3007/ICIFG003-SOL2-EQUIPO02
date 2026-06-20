package com.backendpet.controller;

import com.backendpet.entity.Carrito;
import com.backendpet.repository.CarritoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "http://localhost:4200") // Permite la conexión con Angular
public class CarritoController {

    private Logger logger = LoggerFactory.getLogger(CarritoController.class);

    @Autowired
    private CarritoRepository carritoRepository;

    @PostMapping("/agregar")
    public Carrito guardarCarritoFrontend(@RequestBody Carrito carrito) {
        logger.info("este es un logger - Recibiendo datos de compra en el carrito desde Angular");
        return carritoRepository.save(carrito); // Guarda directo en la base de datos
    }
}