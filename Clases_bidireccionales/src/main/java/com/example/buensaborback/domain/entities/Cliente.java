package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Cliente extends Base{

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Pedido> pedidos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "Cliente_domicilio",
            joinColumns = @JoinColumn(name = "Cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "domicilio_id"))
    @Builder.Default
    private Set<Domicilio> domicilios = new HashSet<>();
    
    @OneToOne
    private Imagen imagen;

}
