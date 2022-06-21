package com.example.locadora.api.controller;

import com.example.locadora.service.aluguel.AdicionalService;
import com.example.locadora.api.dto.AdicionalDTO;
import com.example.locadora.model.entity.aluguel.Adicional;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/adicional")
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
}
