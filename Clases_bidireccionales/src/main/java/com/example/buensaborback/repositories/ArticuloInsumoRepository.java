package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticuloInsumoRepository extends JpaRepository<ArticuloInsumo,Long> {

    @Query("SELECT ai " +
            "FROM ArticuloInsumo ai " +
            "WHERE :denominacion IS NOT NULL AND LOWER(ai.denominacion) LIKE LOWER(CONCAT('%', :denominacion, '%'))")
    List<ArticuloInsumo> searchByDenominacion(@Param("denominacion") String denominacion);
}
