package com.example.buensaborback.Services;

import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.repositories.ArticuloInsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloInsumoService implements BaseService<ArticuloInsumo>{

    @Autowired
    public ArticuloInsumoRepository articuloInsumoRepository;

    @Override
    public List<ArticuloInsumo> findAll() throws Exception {
        return null;
    }

    @Override
    public ArticuloInsumo findById(Long id) throws Exception {
        return null;
    }

    @Override
    public ArticuloInsumo save(ArticuloInsumo entity) throws Exception {
        return null;
    }

    @Override
    public ArticuloInsumo update(Long id, ArticuloInsumo entity) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }

    public List<ArticuloInsumo> searchByDenominacion(String denominacion) throws Exception{
        try{
            List<ArticuloInsumo> articuloInsumos = articuloInsumoRepository.searchByDenominacion(denominacion);
            return articuloInsumos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
