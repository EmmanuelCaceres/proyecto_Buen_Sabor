package com.example.buensaborback.controllers;

import com.example.buensaborback.Services.ArticuloManufacturadoService;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="articuloManufacturado")
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
//            if(!imagen.isEmpty()){
//                Path directorioImagenes = Paths.get("C:\\Users\\caemm\\Documents\\Tecnicatura\\Cuarto semestre\\Buen_sabor\\FrontEnd_React\\src\\assets\\imagenes");
//                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
//
//                byte[] byteImg = imagen.getBytes();
//                Path rutaCompleta = Paths.get(rutaAbsoluta+"imagen");
//            }
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
