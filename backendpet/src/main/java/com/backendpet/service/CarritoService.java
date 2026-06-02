package com.backendpet.service;

import com.backendpet.dto.AgregarAlCarritoRequest;
import com.backendpet.dto.CarritoResponseDTO;
import com.backendpet.entity.Carrito;
import com.backendpet.entity.Cliente;
import com.backendpet.entity.DetalleCarrito;
import com.backendpet.entity.Producto;
import com.backendpet.repository.CarritoRepository;
import com.backendpet.repository.ClienteRepository;
import com.backendpet.repository.DetalleCarritoRepository;
import com.backendpet.repository.ProductoRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> obtenerTodos() {
        return carritoRepository.findAll();
    }

    public Carrito obtenerPorId(Integer id) {
        return carritoRepository.findById(id).orElse(null);
    }

    public Carrito guardar(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public void eliminar(Integer id) {
        carritoRepository.deleteById(id);
    }

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    @Transactional
    public CarritoResponseDTO agregarProducto(AgregarAlCarritoRequest request) {
        // Buscar o crear el carrito activo del cliente
        Cliente cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Carrito carrito = carritoRepository.findAll().stream()
                .filter(c -> c.getCliente() != null &&
                            c.getCliente().getId().equals(request.getIdCliente()))
                .findFirst()
                .orElseGet(() -> {
                    Carrito nuevo = new Carrito();
                    nuevo.setCliente(cliente);
                    nuevo.setFechaCreacion(java.time.LocalDateTime.now());
                    return carritoRepository.save(nuevo);
                });

        // Buscar el producto
        Producto producto = productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Agregar el detalle
        DetalleCarrito detalle = new DetalleCarrito();
        detalle.setCarrito(carrito);
        detalle.setProducto(producto);
        detalle.setCantidad(request.getCantidad());
        detalle.setPrecioUnitario(producto.getPrecio());
        detalleCarritoRepository.save(detalle);

        // Construir la respuesta con todos los detalles del carrito
        List<DetalleCarrito> detalles = detalleCarritoRepository.findAll().stream()
                .filter(d -> d.getCarrito().getId().equals(carrito.getId()))
                .toList();

        List<CarritoResponseDTO.ItemCarritoDTO> items = detalles.stream()
                .map(d -> new CarritoResponseDTO.ItemCarritoDTO(
                    d.getProducto().getNombre(),
                    d.getCantidad(),
                    d.getPrecioUnitario()
                )).toList();

        BigDecimal total = detalles.stream()
                .map(d -> d.getPrecioUnitario()
                        .multiply(BigDecimal.valueOf(d.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CarritoResponseDTO(carrito.getId(), items, total);
    }
}
