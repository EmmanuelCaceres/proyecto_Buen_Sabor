package com.example.buensaborback.Services;

import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements BaseService<Categoria>{

    @Autowired
    public CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public List<Categoria> findAll() throws Exception {
        try{
            List<Categoria> entities = categoriaRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Categoria findById(Long id) throws Exception {
        try{
            Optional<Categoria> entityOptional = categoriaRepository.findById(id);
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());

        }
    }

    @Override
    @Transactional
    public Categoria save(Categoria entity) throws Exception {
        try{
            entity = categoriaRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Categoria update(Long id, Categoria entity) throws Exception {
        try{
            Optional<Categoria> entityOptional = categoriaRepository.findById(id);
            Categoria categoria = entityOptional.get();
            categoria = categoriaRepository.save(entity);
            return categoria;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try{
            if (categoriaRepository.existsById(id)){

                categoriaRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Categoria> getbyName(String nombre) throws Exception {
        try{
            List<Categoria> entities;
            if(!nombre.isEmpty()){
                entities = categoriaRepository.getByName(nombre);
            }else {
                entities = categoriaRepository.findAll();
            }
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Categoria findWithSucursalesById(Long id) throws Exception {
        try {
            return categoriaRepository.findWithSucursalesById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Categoria> findCategoriesWithoutSubCategories() throws Exception {
        try {
            return categoriaRepository.findCategoriesWithoutSubCategories();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
