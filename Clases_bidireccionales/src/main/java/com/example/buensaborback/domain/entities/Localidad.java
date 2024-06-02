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
public class Localidad extends Base{

    private String nombre;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    @OneToMany(mappedBy = "localidad", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Builder.Default
    @JsonBackReference
    private Set<Domicilio> domicilios = new HashSet<>();
}
