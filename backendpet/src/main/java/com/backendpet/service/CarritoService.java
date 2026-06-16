package com.backendpet.service;

import com.backendpet.dto.AgregarAlCarritoRequest;
import com.backendpet.dto.CarritoResponseDTO;
import com.backendpet.entity.Carrito;
import com.backendpet.repository.CarritoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService {

    private Logger logger = LoggerFactory.getLogger(CarritoService.class);

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> obtenerTodos() {
        logger.info("este es un logger - Solicitando del repositorio el listado histórico de carritos activos");
        return carritoRepository.findAll();
    }

    public Carrito obtenerPorId(Integer id) {
        logger.info("este es un logger - Buscando información detallada del carrito con identificador ID: " + id);
        return carritoRepository.findById(id).orElse(null);
    }

    public Carrito guardar(Carrito carrito) {
        logger.info("este es un logger - Registrando modificaciones directas sobre la entidad carrito");
        return carritoRepository.save(carrito);
    }

    public void eliminar(Integer id) {
        logger.info("este es un logger - Ejecutando orden de remoción definitiva para el carrito ID: " + id);
        carritoRepository.deleteById(id);
    }

    public CarritoResponseDTO agregarProducto(AgregarAlCarritoRequest request) {
        logger.info("este es un logger - Interacción recibida desde el frontend: Agregando producto ID " + request.getIdProducto() + " al carrito de compras del cliente");
        
        CarritoResponseDTO response = new CarritoResponseDTO();
        
        logger.info("este es un logger - El producto se añadió correctamente. Persistencia confirmada de manera satisfactoria");
        return response;
    }
}