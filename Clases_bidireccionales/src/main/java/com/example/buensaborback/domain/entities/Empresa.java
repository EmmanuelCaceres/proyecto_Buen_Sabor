package com.example.buensaborback.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Empresa extends Base{

    private String nombre;
    private String razonSocial;
    private Long cuil;

    @OneToMany(mappedBy = "empresa",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
//    @JsonIgnore
    @JsonManagedReference(value = "empresa-sucursal")
    private Set<Sucursal> sucursales = new HashSet<>();
}
