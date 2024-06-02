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
public class Provincia extends Base{

    private String nombre;

    @ManyToOne(cascade = CascadeType.PERSIST) // Considera usar FetchType.LAZY si es posible
    @JoinColumn(name = "id_pais")
    private Pais pais;

    @OneToMany(mappedBy = "provincia",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    @JsonBackReference

    private Set<Localidad> localidades = new HashSet<>();

}
