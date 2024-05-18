package com.example.buensaborback.controllers;

import com.example.buensaborback.Services.ArticuloInsumoService;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "articuloInsumo")
public class ArticuloInsumoController {

    @Autowired
    public ArticuloInsumoService articuloInsumoService;

    @GetMapping("/search")
    public ResponseEntity<?> searchByDenominacion(@RequestParam String denominacion){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(articuloInsumoService.searchByDenominacion(denominacion));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }
}
