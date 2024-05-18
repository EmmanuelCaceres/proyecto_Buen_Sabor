package com.example.buensaborback.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class ArticuloManufacturadoDetalle extends Base {
    private Double cantidad;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "articulo_insumo_id")
    private ArticuloInsumo articuloInsumo;

}
