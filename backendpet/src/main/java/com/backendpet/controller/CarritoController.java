package com.backendpet.controller;

import com.backendpet.dto.AgregarAlCarritoRequest;
import com.backendpet.entity.Carrito;
import com.backendpet.entity.Cliente;
import com.backendpet.entity.DetalleCarrito;
import com.backendpet.entity.Producto;
import com.backendpet.repository.CarritoRepository;
import com.backendpet.repository.ClienteRepository;
import com.backendpet.repository.DetalleCarritoRepository;
import com.backendpet.repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "*")
public class CarritoController {

    private Logger logger = LoggerFactory.getLogger(CarritoController.class);

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    @PostMapping("/agregar")
    public ResponseEntity<?> guardarCarritoFrontend(@RequestBody AgregarAlCarritoRequest request) {
        logger.info("este es un logger - Recibiendo datos de compra en el carrito desde Angular");

        Cliente cliente = clienteRepository.findById(request.getIdCliente()).orElse(null);
        if (cliente == null) {
            logger.info("este es un logger - Cliente no encontrado con ID: " + request.getIdCliente());
            return ResponseEntity.badRequest().body("Cliente no encontrado");
        }

        Producto producto = productoRepository.findById(request.getIdProducto()).orElse(null);
        if (producto == null) {
            logger.info("este es un logger - Producto no encontrado con ID: " + request.getIdProducto());
            return ResponseEntity.badRequest().body("Producto no encontrado");
        }

        Carrito carrito = carritoRepository.findAll().stream()
            .filter(c -> c.getCliente() != null &&
                         c.getCliente().getId().equals(request.getIdCliente()))
            .findFirst()
            .orElseGet(() -> {
                Carrito nuevo = new Carrito();
                nuevo.setCliente(cliente);
                nuevo.setFechaCreacion(LocalDateTime.now());
                return carritoRepository.save(nuevo);
            });

        DetalleCarrito detalle = new DetalleCarrito();
        detalle.setCarrito(carrito);
        detalle.setProducto(producto);
        detalle.setCantidad(request.getCantidad());
        detalle.setPrecioUnitario(producto.getPrecio());
        detalleCarritoRepository.save(detalle);

        logger.info("este es un logger - Producto ID " + request.getIdProducto() + " agregado exitosamente al carrito ID " + carrito.getId());
        return ResponseEntity.ok().build();
    }
}