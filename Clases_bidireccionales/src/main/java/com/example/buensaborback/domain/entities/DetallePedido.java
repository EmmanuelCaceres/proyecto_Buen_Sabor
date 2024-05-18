package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class DetallePedido extends Base{

    private Integer cantidad;
    private Double subTotal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

}
