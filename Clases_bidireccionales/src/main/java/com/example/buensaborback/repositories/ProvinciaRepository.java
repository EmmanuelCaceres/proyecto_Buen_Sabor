package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends BaseRepository<Provincia,Long> {

    @Query("SELECT p FROM Provincia p WHERE p.nombre LIKE :provinciaNombre")
    Provincia findByNombre(@Param("provinciaNombre") String provinciaNombre);
}
