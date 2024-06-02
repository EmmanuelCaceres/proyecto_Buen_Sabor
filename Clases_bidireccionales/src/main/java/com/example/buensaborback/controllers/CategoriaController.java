package com.example.buensaborback.controllers;

import com.example.buensaborback.Services.impl.CategoriaService;
import com.example.buensaborback.domain.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="categorias")
public class CategoriaController {
    @Autowired
    public CategoriaService categoriaService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findAll());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findById(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }
    @GetMapping("/name")
    public ResponseEntity<?> getByName(@RequestParam String nombre){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoriaService.getbyName(nombre));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody Categoria entity){
        try {

//            if(!imagen.isEmpty()){
//                Path directorioImagenes = Paths.get("C:\\Users\\caemm\\Documents\\Tecnicatura\\Cuarto semestre\\Buen_sabor\\FrontEnd_React\\src\\assets\\imagenes");
//                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
//
//                byte[] byteImg = imagen.getBytes();
//                Path rutaCompleta = Paths.get(rutaAbsoluta+"imagen");
//            }
            return ResponseEntity.status(HttpStatus.OK).body(categoriaService.save(entity));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Categoria entity){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoriaService.update(id,entity));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoriaService.delete(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }
}
