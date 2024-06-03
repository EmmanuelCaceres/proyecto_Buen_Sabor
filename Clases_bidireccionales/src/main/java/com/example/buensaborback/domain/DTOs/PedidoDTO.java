package com.example.buensaborback.domain.DTOs;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PedidoDTO {
    private Long id;
    private boolean eliminado;
    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private String estado;
    private String tipoEnvio;
    private String formaPago;
    private LocalDate fechaPedido;
    private List<DetallePedidoDTO> detallePedidos;
}
