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
public class Imagen extends Base{

    private String url;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="articulo_id")
    private Articulo articulo;
    
}
