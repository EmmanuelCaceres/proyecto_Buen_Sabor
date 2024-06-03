package com.example.buensaborback.controllers;

import com.example.buensaborback.Services.impl.ArticuloInsumoService;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "articuloInsumos")
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

    /*public ArticuloInsumoController(ArticuloManufacturadoService articuloManufacturadoService){
        this.articuloInsumoService = articuloInsumoService;
    }*/

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(articuloInsumoService.findAll());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(articuloInsumoService.findById(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }
    @GetMapping("/name")
    public ResponseEntity<?> getByName(@RequestParam String nombre){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(articuloInsumoService.getbyName(nombre));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody ArticuloInsumo entity){
        try {

//            if(!imagen.isEmpty()){
//                Path directorioImagenes = Paths.get("C:\\Users\\caemm\\Documents\\Tecnicatura\\Cuarto semestre\\Buen_sabor\\FrontEnd_React\\src\\assets\\imagenes");
//                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
//
//                byte[] byteImg = imagen.getBytes();
//                Path rutaCompleta = Paths.get(rutaAbsoluta+"imagen");
//            }
            return ResponseEntity.status(HttpStatus.OK).body(articuloInsumoService.save(entity));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody ArticuloInsumo entity){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(articuloInsumoService.update(id,entity));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(articuloInsumoService.delete(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("insumosParaVentas")
    public ResponseEntity<?> getArticulosParaVenta(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(articuloInsumoService.getArticulosParaVenta());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }
}
