package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends BaseRepository<Categoria,Long> {
    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.sucursales WHERE c.id = :id")
    Categoria findWithSucursalesById(@Param("id") Long id);

    @Query("SELECT c FROM Categoria c   WHERE c.subCategorias IS NULL")
    List<Categoria> findCategoriesWithoutSubCategories();

    @Query("SELECT c FROM Categoria c WHERE :denominacion IS NOT NULL AND LOWER(c.denominacion) LIKE LOWER(CONCAT('%', :denominacion, '%'))")
    List<Categoria> getByName (@Param("denominacion") String denominacion);
}
