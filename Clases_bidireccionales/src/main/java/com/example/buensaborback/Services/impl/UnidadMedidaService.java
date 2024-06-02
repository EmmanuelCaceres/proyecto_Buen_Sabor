package com.example.buensaborback.Services.impl;

import com.example.buensaborback.Services.BaseService;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.UnidadMedida;
import com.example.buensaborback.repositories.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaService implements BaseService<UnidadMedida> {

    @Autowired
    public UnidadMedidaRepository unidadMedidaRepository;

    @Override
    public List<UnidadMedida> findAll() throws Exception {
        try{
            List<UnidadMedida> entities = unidadMedidaRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UnidadMedida findById(Long id) throws Exception {
        return null;
    }

    @Override
    public UnidadMedida save(UnidadMedida entity) throws Exception {
        return null;
    }

    @Override
    public UnidadMedida update(Long id, UnidadMedida entity) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }
}
