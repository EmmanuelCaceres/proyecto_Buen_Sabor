package com.example.buensaborback.domain.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CategoriaDTO {
    private Long id;
    private boolean eliminado;
    private String denominacion;
}
