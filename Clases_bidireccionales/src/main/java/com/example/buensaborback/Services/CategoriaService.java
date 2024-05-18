package com.example.buensaborback.Services;

import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements BaseService<Categoria>{

    @Autowired
    public CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() throws Exception {
        try{
            List<Categoria> entities = categoriaRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Categoria findById(Long id) throws Exception {
        return null;
    }

    @Override
    public Categoria save(Categoria entity) throws Exception {
        return null;
    }

    @Override
    public Categoria update(Long id, Categoria entity) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }
}
