package com.example.locadora.api.controller;

import com.example.locadora.model.entity.carro.MarcaCarro;
import com.example.locadora.service.carro.MarcaCarroService;
import com.example.locadora.api.dto.MarcaCarroDTO;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/marcasCarro")
@RequiredArgsConstructor

public class MarcaCarroController {

    private final MarcaCarroService service;


    @GetMapping()
    public ResponseEntity get() {
        List<MarcaCarro> marcasCarro = service.getMarcasCarro();
        return ResponseEntity.ok(marcasCarro.stream().map(MarcaCarroDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<MarcaCarro> marcaCarro = service.getMarcaCarroById(id);
        if (!marcaCarro.isPresent()) {
            return new ResponseEntity("MarcaCarro não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(marcaCarro.map(MarcaCarroDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(MarcaCarroDTO dto) {
        try {
            MarcaCarro marcaCarro = converter(dto);
            marcaCarro = service.salvar(marcaCarro);
            return new ResponseEntity(marcaCarro, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, MarcaCarroDTO dto) {
        if (!service.getMarcaCarroById(id).isPresent()) {
            return new ResponseEntity("MarcaCarro não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            MarcaCarro marcaCarro = converter(dto);
            marcaCarro.setId(id);
            service.salvar(marcaCarro);
            return ResponseEntity.ok(marcaCarro);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<MarcaCarro> marcaCarro = service.getMarcaCarroById(id);
        if (!marcaCarro.isPresent()) {
            return new ResponseEntity("MarcaCarro não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(marcaCarro.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public MarcaCarro converter(MarcaCarroDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, MarcaCarro.class);
    }
}
