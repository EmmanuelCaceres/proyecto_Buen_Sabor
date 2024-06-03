package com.example.buensaborback.Services.impl;

import com.example.buensaborback.Services.BaseService;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.repositories.ArticuloInsumoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloInsumoService implements BaseService<ArticuloInsumo> {

    @Autowired
    public ArticuloInsumoRepository articuloInsumoRepository;

    @Override
    @Transactional
    public List<ArticuloInsumo> findAll() throws Exception {
        try{
            List<ArticuloInsumo> entities = articuloInsumoRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ArticuloInsumo findById(Long id) throws Exception {
        try{
            Optional<ArticuloInsumo> entityOptional = articuloInsumoRepository.findById(id);
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());

        }
    }

    @Override
    @Transactional
    public ArticuloInsumo save(ArticuloInsumo entity) throws Exception {
        try{
            entity = articuloInsumoRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ArticuloInsumo update(Long id, ArticuloInsumo entity) throws Exception {
        try{
            Optional<ArticuloInsumo> entityOptional = articuloInsumoRepository.findById(id);
            ArticuloInsumo articuloInsumo = entityOptional.get();
            articuloInsumo = articuloInsumoRepository.save(entity);
            return articuloInsumo;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try{
            if (articuloInsumoRepository.existsById(id)){

                articuloInsumoRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<ArticuloInsumo> searchByDenominacion(String denominacion) throws Exception{
        try{
            List<ArticuloInsumo> articuloInsumos = articuloInsumoRepository.searchByDenominacion(denominacion);
            return articuloInsumos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<ArticuloInsumo> getbyName(String nombre) throws Exception {
        try{
            List<ArticuloInsumo> entities;
            if(!nombre.isEmpty()){
                entities = articuloInsumoRepository.getByName(nombre);
            }else {
                entities = articuloInsumoRepository.findAll();
            }
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<ArticuloInsumo> getArticulosParaVenta(){
        return articuloInsumoRepository.getArticulosParaVenta();
    }
}
