package com.example.buensaborback.domain.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class UsuarioCliente extends Base{
    private String auth0Id;
    private String userName;


}
