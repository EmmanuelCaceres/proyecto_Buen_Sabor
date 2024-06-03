package com.example.buensaborback.domain.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class DetallePedidoDTO {
    private Long id;
    private boolean eliminado;
    private Integer cantidad;
    private Double subTotal;
    private ArticuloDTO articulo;
}
