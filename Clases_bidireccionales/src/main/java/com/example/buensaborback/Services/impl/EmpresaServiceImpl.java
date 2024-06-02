package com.example.buensaborback.Services.impl;

import com.example.buensaborback.Services.IEmpresaService;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements IEmpresaService {

    @Autowired
    public EmpresaRepository empresaRepository;

    @Override
    public List<Empresa> findAll() throws Exception {
        return empresaRepository.findAll();
    }

    @Override
    public Empresa findById(Long id) throws Exception {
        return null;
    }

    @Override
    public Empresa save(Empresa entity) throws Exception {
        try{
            entity = empresaRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Empresa update(Long id, Empresa entity) throws Exception {
        try{
            Optional<Empresa> entityOptional = empresaRepository.findById(id);
            Empresa empresa= entityOptional.get();
            empresa = empresaRepository.save(entity);
            return empresa;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }
}
