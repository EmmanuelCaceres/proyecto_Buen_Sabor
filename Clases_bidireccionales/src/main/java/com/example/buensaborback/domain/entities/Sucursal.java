package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Sucursal extends Base{

    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="empresa_id")
    private Empresa empresa;

    @OneToOne
    private Domicilio domicilio;
    
    @ManyToMany
    //SE AGREGA EL JOIN TABLE PARA QUE JPA CREE LA TABLA INTERMEDIA EN UNA RELACION MANY TO MANY
    @JoinTable(name = "sucursal_categoria",
            joinColumns = @JoinColumn(name = "sucursal_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    //SE AGREGA EL BUILDER.DEFAULT PARA QUE BUILDER NO SOBREESCRIBA LA INICIALIZACION DE LA LISTA
    @Builder.Default
    private Set<Categoria> categorias = new HashSet<>();

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Promocion> promociones = new HashSet<>();

    @OneToMany(mappedBy = "sucursal",cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Pedido> pedidos = new HashSet<>();

}
