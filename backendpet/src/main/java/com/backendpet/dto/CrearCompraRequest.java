package com.backendpet.dto;

import lombok.Data;

import java.util.List;

@Data
public class CrearCompraRequest {

    private ClienteCompraDTO cliente;
    private List<ProductoCompraDTO> productos;
    private String tipoRetiro;
    private String observaciones;

    @Data
    public static class ClienteCompraDTO {
        private String rut;
        private String nombre;
        private String apellido;
        private String correo;
        private String telefono;
        private String direccion;
    }

    @Data
    public static class ProductoCompraDTO {
        private Integer idProducto;
        private Integer cantidad;
    }
}