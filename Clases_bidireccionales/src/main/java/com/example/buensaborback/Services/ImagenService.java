package com.example.buensaborback.Services;

import com.example.buensaborback.domain.entities.ImagenArticulo;
import com.example.buensaborback.repositories.ImagenArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ImagenService implements BaseService<ImagenArticulo>{

    @Autowired
    private ImagenArticuloRepository imagenArticuloRepository;

    @Override
    public List<ImagenArticulo> findAll() throws Exception {
        return null;
    }

    @Override
    public ImagenArticulo findById(Long id) throws Exception {
        return null;
    }

    @Override
    public ImagenArticulo save(ImagenArticulo entity) throws Exception {
        try{
            entity = imagenArticuloRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ImagenArticulo update(Long id, ImagenArticulo entity) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }

    public Resource load(String filename) throws MalformedURLException {
        Path path = getPath(filename);
        Resource resource = new UrlResource(path.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Error in path: " + path.toString());
        }
        return resource;
    }
//
//    public String copy(MultipartFile file) throws IOException {
//        String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//        Path rootPath = getPath(uniqueFilename);
//        Files.copy(file.getInputStream(), rootPath);
//        return uniqueFilename;
//    }
//
//    public boolean delete(String filename) {
//        Path rootPath = getPath(filename);
//        File file = rootPath.toFile();
//        if(file.exists() && file.canRead()) {
//            if(file.delete()) {
//                return true;
//            }
//        }
//        return false;
//    }

    public Path getPath(String filename) {
        return Paths.get("uploads/").resolve(filename).toAbsolutePath();
    }
}
