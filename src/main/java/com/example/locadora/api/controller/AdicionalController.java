package com.example.locadora.api.controller;

import com.example.locadora.model.entity.aluguel.Adicional;
import com.example.locadora.service.aluguel.AdicionalService;
import com.example.locadora.api.dto.AdicionalDTO;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/adicionais")
@RequiredArgsConstructor

public class AdicionalController {
    private final AdicionalService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Adicional> adicionais = service.getAdicionais();
        return ResponseEntity.ok(adicionais.stream().map(AdicionalDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Adicional> adicional = service.getAdicionalById(id);
        if (!adicional.isPresent()) {
            return new ResponseEntity("Adicional não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adicional.map(AdicionalDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(AdicionalDTO dto) {
        try {
            Adicional adicional = converter(dto);
            adicional = service.salvar(adicional);
            return new ResponseEntity(adicional, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, AdicionalDTO dto) {
        if (!service.getAdicionalById(id).isPresent()) {
            return new ResponseEntity("Adicional não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Adicional adicional = converter(dto);
            adicional.setId(id);
            service.salvar(adicional);
            return ResponseEntity.ok(adicional);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Adicional> adicional = service.getAdicionalById(id);
        if (!adicional.isPresent()) {
            return new ResponseEntity("Adicional não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(adicional.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Adicional converter(AdicionalDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Adicional.class);
    }
}
