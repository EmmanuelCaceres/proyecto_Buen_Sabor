package com.example.buensaborback.controllers;

import com.example.buensaborback.Services.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="imagenArticulo")
public class ImagenArticuloController {
    @Autowired
    public ImagenService imagenService;

//    @PostMapping("")
//    public ResponseEntity<?> uploadImage (@RequestPart("imagen") MultipartFile image){
//        try {
//            if(!image.isEmpty()){
//                String imageUrl = imagenService.copy(image);
//            }
//
////            if(!imagen.isEmpty()){
////                Path directorioImagenes = Paths.get("C:\\Users\\caemm\\Documents\\Tecnicatura\\Cuarto semestre\\Buen_sabor\\FrontEnd_React\\src\\assets\\imagenes");
////                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
////
////                byte[] byteImg = imagen.getBytes();
////                Path rutaCompleta = Paths.get(rutaAbsoluta+"imagen");
////            }
//            return ResponseEntity.status(HttpStatus.OK).body(articuloManufacturadoService.save(entity));
//        }catch(Exception e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
//        }
//    }
}
