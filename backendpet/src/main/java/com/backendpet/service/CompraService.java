package com.backendpet.service;

import com.backendpet.dto.CompraResponseDTO;
import com.backendpet.dto.CrearCompraRequest;
import com.backendpet.entity.Cliente;
import com.backendpet.entity.Compra;
import com.backendpet.entity.DetalleCompra;
import com.backendpet.entity.Producto;
import com.backendpet.repository.ClienteRepository;
import com.backendpet.repository.CompraRepository;
import com.backendpet.repository.DetalleCompraRepository;
import com.backendpet.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CompraService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Transactional
    public CompraResponseDTO crearCompra(CrearCompraRequest request) {
        if (request.getCliente() == null) {
            throw new RuntimeException("Debe ingresar los datos del cliente.");
        }

        if (request.getProductos() == null || request.getProductos().isEmpty()) {
            throw new RuntimeException("El carrito está vacío.");
        }

        String correo = limpiarTexto(request.getCliente().getCorreo());

        Cliente cliente = clienteRepository.findByCorreo(correo).orElse(new Cliente());

        cliente.setRut(limpiarTexto(request.getCliente().getRut()));
        cliente.setNombre(limpiarTexto(request.getCliente().getNombre()));
        cliente.setApellido(limpiarTexto(request.getCliente().getApellido()));
        cliente.setCorreo(correo);
        cliente.setTelefono(limpiarTexto(request.getCliente().getTelefono()));
        cliente.setDireccion(limpiarTexto(request.getCliente().getDireccion()));

        if (cliente.getFechaRegistro() == null) {
            cliente.setFechaRegistro(LocalDate.now());
        }

        Cliente clienteGuardado = clienteRepository.save(cliente);

        Compra compra = new Compra();
        compra.setCliente(clienteGuardado);
        compra.setFechaCompra(LocalDateTime.now());
        compra.setEstado("CONFIRMADA");
        compra.setTipoRetiro(
                request.getTipoRetiro() == null || request.getTipoRetiro().isBlank()
                        ? "RETIRO_EN_TIENDA"
                        : request.getTipoRetiro()
        );
        compra.setObservaciones(limpiarTexto(request.getObservaciones()));
        compra.setTotal(BigDecimal.ZERO);

        Compra compraGuardada = compraRepository.save(compra);

        BigDecimal total = BigDecimal.ZERO;

        for (CrearCompraRequest.ProductoCompraDTO item : request.getProductos()) {
            Producto producto = productoRepository.findById(item.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + item.getIdProducto()));

            int cantidad = item.getCantidad() == null || item.getCantidad() <= 0
                    ? 1
                    : item.getCantidad();

            int stockActual = producto.getStock() == null ? 0 : producto.getStock();

            if (stockActual < cantidad) {
                throw new RuntimeException("No hay stock suficiente para el producto: " + producto.getNombre());
            }

            BigDecimal precioUnitario = producto.getPrecio();
            BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));

            DetalleCompra detalle = new DetalleCompra();
            detalle.setCompra(compraGuardada);
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(precioUnitario);
            detalle.setSubtotal(subtotal);

            detalleCompraRepository.save(detalle);

            producto.setStock(stockActual - cantidad);
            productoRepository.save(producto);

            total = total.add(subtotal);
        }

        compraGuardada.setTotal(total);
        Compra compraFinal = compraRepository.save(compraGuardada);

        return new CompraResponseDTO(
                compraFinal.getId(),
                compraFinal.getFechaCompra(),
                compraFinal.getTotal(),
                compraFinal.getEstado(),
                "Compra confirmada correctamente. El pago y retiro se realizan en tienda."
        );
    }

    private String limpiarTexto(String texto) {
        return texto == null ? "" : texto.trim();
    }
}