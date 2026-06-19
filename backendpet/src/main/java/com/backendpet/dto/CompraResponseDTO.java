package com.backendpet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraResponseDTO {

    private Integer idCompra;
    private LocalDateTime fechaCompra;
    private BigDecimal total;
    private String estado;
    private String mensaje;
}