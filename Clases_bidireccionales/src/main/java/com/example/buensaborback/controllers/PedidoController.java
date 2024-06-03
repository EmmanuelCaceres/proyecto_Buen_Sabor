package com.example.buensaborback.controllers;

import com.example.buensaborback.Services.impl.PedidoServiceImpl;
import com.example.buensaborback.domain.DTOs.PedidoDTO;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="pedido")
public class PedidoController {
    @Autowired
    private PedidoServiceImpl pedidoService;


    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAll());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody PedidoDTO pedidoDto){
        try {
            Pedido pedido = pedidoService.convertirDtoAEntidad(pedidoDto);
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.save(pedido));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error por favor intente mas tarde.\"}");
        }
    }

}
