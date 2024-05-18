package com.example.buensaborback.Services;

import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.repositories.ArticuloManufacturadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloManufacturadoService implements BaseService<ArticuloManufacturado>{
    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Override
    @Transactional
    public List<ArticuloManufacturado> findAll() throws Exception {
        try{
            List<ArticuloManufacturado> entities = articuloManufacturadoRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ArticuloManufacturado findById(Long id) throws Exception {
        try{
            Optional<ArticuloManufacturado> entityOptional = articuloManufacturadoRepository.findById(id);
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());

        }
    }

    @Override
    @Transactional
    public ArticuloManufacturado save(ArticuloManufacturado entity) throws Exception {
        try{
            entity = articuloManufacturadoRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ArticuloManufacturado update(Long id, ArticuloManufacturado entity) throws Exception {
        try{
            Optional<ArticuloManufacturado> entityOptional = articuloManufacturadoRepository.findById(id);
            ArticuloManufacturado articuloManufacturado = entityOptional.get();
            articuloManufacturado = articuloManufacturadoRepository.save(entity);
            return articuloManufacturado;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try{
            if (articuloManufacturadoRepository.existsById(id)){

                articuloManufacturadoRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<ArticuloManufacturado> getbyName(String nombre) throws Exception {
        try{
            List<ArticuloManufacturado> entities;
            if(!nombre.isEmpty()){
                entities = articuloManufacturadoRepository.getByName(nombre);
            }else {
                entities = articuloManufacturadoRepository.findAll();
            }
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
