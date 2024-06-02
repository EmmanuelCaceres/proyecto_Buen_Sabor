package com.example.buensaborback.controllers;

import com.example.buensaborback.Services.impl.ArticuloManufacturadoService;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="articuloManufacturados")
public class ArticuloManufacturadoController {
    private ArticuloManufacturadoService articuloManufacturadoService;

    public ArticuloManufacturadoController(ArticuloManufacturadoService articuloManufacturadoService){
        this.articuloManufacturadoService = articuloManufacturadoService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(articuloManufacturadoService.findAll());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(articuloManufacturadoService.findById(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }
    @GetMapping("/name")
    public ResponseEntity<?> getByName(@RequestParam String nombre){
            try {
                return ResponseEntity.status(HttpStatus.OK).body(articuloManufacturadoService.getbyName(nombre));
            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
            }
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody ArticuloManufacturado entity){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(articuloManufacturadoService.save(entity));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody ArticuloManufacturado entity){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(articuloManufacturadoService.update(id,entity));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(articuloManufacturadoService.delete(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }
}
