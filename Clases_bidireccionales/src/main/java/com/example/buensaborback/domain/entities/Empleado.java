package com.example.buensaborback.domain.entities;

import com.example.buensaborback.domain.entities.enums.Rol;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Empleado extends Base{


    private Rol tipoEmpleado;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;

    @OneToOne
    private UsuarioEmpleado usuarioEmpleado;

    @OneToOne
    private ImagenEmpleado imagenEmpleado;


    @OneToMany(mappedBy = "empleado", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private Set<Pedido> pedidos= new HashSet<>();

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
}
