package com.example.locadora.api.controller;

import com.example.locadora.api.dto.CarroDTO;
import com.example.locadora.model.entity.carro.Carro;
import com.example.locadora.service.carro.CarroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/carros")
@RequiredArgsConstructor

public class CarroController {
    private final CarroService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Carro> carros = service.getCarros();
        return ResponseEntity.ok(carros.stream().map(CarroDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Carro> carro = service.getCarroById(id);
        if (!carro.isPresent()) {
            return new ResponseEntity("Carro não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(carro.map(CarroDTO::create));
    }
}
