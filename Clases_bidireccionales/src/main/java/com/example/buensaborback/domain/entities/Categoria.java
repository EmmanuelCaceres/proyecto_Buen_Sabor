package com.example.buensaborback.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Categoria extends Base{

    private String denominacion;


    @ManyToMany(mappedBy = "categorias")
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();

    @OneToMany(mappedBy = "categoria",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    @JsonBackReference(value = "articuloManufacturado")
    private Set<Articulo> articulos = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "categoria_id")
    @Builder.Default
    private Set<Categoria> subCategorias = new HashSet<>();

}
