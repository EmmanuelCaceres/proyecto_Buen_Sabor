package com.example.buensaborback.controllers;

import com.example.buensaborback.Services.ImagenService;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="imagenArticulo")
public class ImagenArticuloController {

    private static String UPLOAD_DIR = "uploads/";
    @Autowired
    public ImagenService imagenService;

    @PostMapping("")
    public ResponseEntity<?> uploadImage (@RequestParam("file") MultipartFile image){
        if (image.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file!");
        }
        try {
            byte[] bytes = image.getBytes();
            Path path = Paths.get(UPLOAD_DIR + image.getOriginalFilename());
            Files.write(path, bytes);

//            Seteamos la url de la imagen(nombre) para enviarla a la base de datos

            ImagenArticulo imagen = new ImagenArticulo();
            imagen.setUrl(image.getOriginalFilename());

            return ResponseEntity.status(HttpStatus.OK).body(imagenService.save(imagen));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping(value = "/uploads/{filename}")
    public ResponseEntity<Resource> goImage(@PathVariable String filename) {
        Resource resource = null;
        try {
            resource = imagenService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
