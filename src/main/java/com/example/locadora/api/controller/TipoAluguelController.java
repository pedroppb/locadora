package com.example.locadora.api.controller;

import com.example.locadora.model.entity.carro.TipoAluguel;
import com.example.locadora.service.carro.TipoAluguelService;
import com.example.locadora.api.dto.TipoAluguelDTO;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tiposAluguel")
@RequiredArgsConstructor

public class TipoAluguelController {
    private final TipoAluguelService service;
    @GetMapping()
    public ResponseEntity get() {
        List<TipoAluguel> TiposAluguel = service.getTiposAluguel();
        return ResponseEntity.ok(TiposAluguel.stream().map(TipoAluguelDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<TipoAluguel> tipoAluguel = service.getTipoAluguelById(id);
        if (!tipoAluguel.isPresent()) {
            return new ResponseEntity("TipoAluguel não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(tipoAluguel.map(TipoAluguelDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(TipoAluguelDTO dto) {
        try {
            TipoAluguel tipoAluguel = converter(dto);
            tipoAluguel = service.salvar(tipoAluguel);
            return new ResponseEntity(tipoAluguel, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, TipoAluguelDTO dto) {
        if (!service.getTipoAluguelById(id).isPresent()) {
            return new ResponseEntity("TipoAluguel não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            TipoAluguel tipoAluguel = converter(dto);
            tipoAluguel.setId(id);
            service.salvar(tipoAluguel);
            return ResponseEntity.ok(tipoAluguel);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<TipoAluguel> tipoAluguel = service.getTipoAluguelById(id);
        if (!tipoAluguel.isPresent()) {
            return new ResponseEntity("TipoAluguel não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(tipoAluguel.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public TipoAluguel converter(TipoAluguelDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, TipoAluguel.class);
    }
}
