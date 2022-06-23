package com.example.locadora.api.controller;


import com.example.locadora.model.entity.carro.MarcaCarro;
import com.example.locadora.service.carro.MarcaCarroService;
import com.example.locadora.model.entity.carro.Modelo;
import com.example.locadora.service.carro.ModeloService;
import com.example.locadora.api.dto.ModeloDTO;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final MarcaCarroService marcaCarroService;


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

    @PostMapping()
    public ResponseEntity post(ModeloDTO dto) {
        try {
            Modelo modelo = converter(dto);
            modelo = service.salvar(modelo);
            return new ResponseEntity(modelo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, ModeloDTO dto) {
        if (!service.getModeloById(id).isPresent()) {
            return new ResponseEntity("Modelo não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Modelo modelo = converter(dto);
            modelo.setId(id);
            service.salvar(modelo);
            return ResponseEntity.ok(modelo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Modelo> modelo = service.getModeloById(id);
        if (!modelo.isPresent()) {
            return new ResponseEntity("Modelo não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(modelo.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Modelo converter(ModeloDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Modelo modelo = modelMapper.map(dto, Modelo.class);
        if (dto.getIdMarcaCarro() != null) {
            Optional<MarcaCarro> marcaCarro = marcaCarroService.getMarcaCarroById(dto.getIdMarcaCarro());
            if (!marcaCarro.isPresent()) {
                modelo.setMarcaCarro(null);
            } else {
                modelo.setMarcaCarro(marcaCarro.get());
            }
        }
   
        return modelo;
    }

}
