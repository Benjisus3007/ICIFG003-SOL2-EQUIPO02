package com.backendpet.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoResponseDTO {

    private Integer idCarrito;
    private List<ItemCarritoDTO> productos;
    private BigDecimal total;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemCarritoDTO {
        private String nombre;
        private Integer cantidad;
        private BigDecimal precioUnitario;
    }
    
}
