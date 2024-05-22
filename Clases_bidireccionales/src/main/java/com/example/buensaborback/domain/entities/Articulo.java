package com.example.buensaborback.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Articulo extends Base implements Serializable {

    /*@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;*/
    protected String denominacion;
    protected Double precioVenta;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Builder.Default
    @JoinColumn(name = "imagen_articulo_id")
//    @NotAudited
    protected Set<ImagenArticulo> imagenes = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "unidad_medida_id")
    protected UnidadMedida unidadMedida;

    @ManyToMany(mappedBy = "articulos")
    @Builder.Default
    protected Set<Promocion> estaEnPromociones = new HashSet<>();

    @OneToMany(mappedBy = "articulo",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Builder.Default
    @JsonIgnoreProperties("articulo")
    protected Set<DetallePedido> detallesPedido = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "categoria_id")
    protected Categoria categoria;

}
