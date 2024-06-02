package com.example.buensaborback.Services;

import com.example.buensaborback.domain.entities.Sucursal;

import java.util.List;

public interface ISucursalService extends BaseService<Sucursal>{

    List<Sucursal> getSucursalesByEmpresaId(Long empresaId);
}
