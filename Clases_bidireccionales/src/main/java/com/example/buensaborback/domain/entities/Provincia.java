package com.example.buensaborback.domain.entities;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pais_id")
    private Pais pais;

    @OneToMany(mappedBy = "provincia",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Localidad> localidades = new HashSet<>();

}
