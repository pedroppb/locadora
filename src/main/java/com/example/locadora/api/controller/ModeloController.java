package com.example.locadora.api.controller;

import com.example.locadora.model.entity.carro.Modelo;
import com.example.locadora.service.carro.ModeloService;
import com.example.locadora.api.dto.ModeloDTO;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/modelos")
@RequiredArgsConstructor

public class ModeloController {

    private final ModeloService service;


    @GetMapping()
    public ResponseEntity get() {
        List<Modelo> modelos = service.getModelos();
        return ResponseEntity.ok(modelos.stream().map(ModeloDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Modelo> modelo = service.getModeloById(id);
        if (!modelo.isPresent()) {
            return new ResponseEntity("Modelo não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelo.map(ModeloDTO::create));
    }

}
