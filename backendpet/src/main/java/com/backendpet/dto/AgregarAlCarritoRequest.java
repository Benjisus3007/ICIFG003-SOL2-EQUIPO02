package com.backendpet.dto;

import lombok.Data;

@Data
public class AgregarAlCarritoRequest {

    private Integer idCliente;
    private Integer idProducto;
    private Integer cantidad;
    
}
