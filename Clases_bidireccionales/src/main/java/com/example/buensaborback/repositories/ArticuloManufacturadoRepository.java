package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticuloManufacturadoRepository extends JpaRepository<ArticuloManufacturado,Long> {
    @Query("SELECT am FROM ArticuloManufacturado am WHERE :denominacion IS NOT NULL AND LOWER(am.denominacion) LIKE LOWER(CONCAT('%', :denominacion, '%'))")
    List<ArticuloManufacturado> getByName (@Param("denominacion") String denominacion);
}
