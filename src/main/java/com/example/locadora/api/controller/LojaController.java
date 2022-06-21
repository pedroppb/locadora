package com.example.locadora.api.controller;

import com.example.locadora.model.entity.aluguel.Loja;
import com.example.locadora.api.dto.LojaDTO;
import com.example.locadora.service.aluguel.LojaService;
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
@RequestMapping("/api/v1/lojas")
@RequiredArgsConstructor

public class LojaController {
        private final LojaService service;


        @GetMapping()
        public ResponseEntity get() {
            List<Loja> lojas = service.getLojas();
            return ResponseEntity.ok(lojas.stream().map(LojaDTO::create).collect(Collectors.toList()));
        }

        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Loja> loja = service.getLojaById(id);
            if (!loja.isPresent()) {
                return new ResponseEntity("Loja não encontrado", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(loja.map(LojaDTO::create));
        }
}
