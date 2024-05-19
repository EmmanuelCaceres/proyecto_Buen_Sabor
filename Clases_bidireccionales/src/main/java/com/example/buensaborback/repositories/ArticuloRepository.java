package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Repository
public interface ArticuloRepository  extends BaseRepository<Articulo, Long> {
}
