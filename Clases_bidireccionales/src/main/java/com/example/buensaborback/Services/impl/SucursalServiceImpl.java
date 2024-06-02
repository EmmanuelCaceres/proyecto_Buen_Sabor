package com.example.buensaborback.Services.impl;

import com.example.buensaborback.Services.ISucursalService;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServiceImpl implements ISucursalService {

    @Autowired
    public SucursalRepository sucursalRepository;

    @Override
    public List<Sucursal> findAll() throws Exception {
        return sucursalRepository.findAll();
    }

    @Override
    public Sucursal findById(Long id) throws Exception {
        return null;
    }

    @Override
    public Sucursal save(Sucursal entity) throws Exception {
        return null;
    }

    @Override
    public Sucursal update(Long id, Sucursal entity) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }

    @Override
    public List<Sucursal> getSucursalesByEmpresaId(Long empresaId) {
        return sucursalRepository.getSucursalesByEmpresaId(empresaId);
    }
}
