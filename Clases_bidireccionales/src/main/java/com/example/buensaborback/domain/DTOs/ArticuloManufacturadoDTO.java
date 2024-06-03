package com.example.buensaborback.domain.DTOs;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class ArticuloManufacturadoDTO extends ArticuloDTO{
    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private String preparacion;
}
