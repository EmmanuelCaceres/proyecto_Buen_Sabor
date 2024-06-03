package com.example.buensaborback.domain.DTOs;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class ArticuloDTO {
    private Long id;
    private boolean eliminado;
    private String denominacion;
    private Double precioVenta;
    private Set<ImagenArticuloDTO> imagenes;
    private UnidadMedidaDTO unidadMedida;
    private CategoriaDTO categoria;
}
