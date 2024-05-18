package com.example.buensaborback.controllers;

import com.example.buensaborback.Services.ArticuloInsumoService;
import com.example.buensaborback.Services.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "unidadMedida")
public class UnidadMedidaController {
    @Autowired
    public UnidadMedidaService unidadMedidaService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(unidadMedidaService.findAll());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }
}
