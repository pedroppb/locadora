package com.example.locadora.api.controller;

import com.example.locadora.service.carro.PrecoService;
import com.example.locadora.api.dto.PrecoDTO;
import com.example.locadora.model.entity.carro.Preco;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/Precos")
@RequiredArgsConstructor

public class PrecoController {
    private final PrecoService service;


    @GetMapping()
    public ResponseEntity get() {
        List<Preco> precos = service.getPrecos();
        return ResponseEntity.ok(precos.stream().map(PrecoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Preco> preco = service.getPrecoById(id);
        if (!preco.isPresent()) {
            return new ResponseEntity("Preco não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(preco.map(PrecoDTO::create));
    }
}
