package com.example.buensaborback.domain.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ImagenArticuloDTO {
    private Long id;
    private boolean eliminado;
    private String url;
}
