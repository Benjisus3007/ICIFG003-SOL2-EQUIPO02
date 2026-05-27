package com.backendpet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "mascota")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @Column(length = 100)
    private String nombre; 
    @Column(length = 50)
    private String especie; 

    @Column(length = 100)
    private String raza; 

    @Column(length = 1)
    private String sexo; 

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento; 

    @Column(precision = 5, scale = 2)
    private BigDecimal peso; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente; 

}