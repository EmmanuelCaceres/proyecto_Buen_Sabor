package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenArticuloRepository extends JpaRepository<ImagenArticulo,Long> {
}
