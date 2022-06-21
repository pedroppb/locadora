package com.example.locadora.api.controller;

import com.example.locadora.model.entity.aluguel.Locacao;
import com.example.locadora.api.dto.LocacaoDTO;
import com.example.locadora.service.aluguel.LocacaoService;
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
@RequestMapping("/api/v1/locacoes")
@RequiredArgsConstructor

public class LocacaoController {
    private final LocacaoService service;


    @GetMapping()
    public ResponseEntity get() {
        List<Locacao> locacoes = service.getLocacoes();
        return ResponseEntity.ok(locacoes.stream().map(LocacaoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Locacao> locacao = service.getLocacaoById(id);
        if (!locacao.isPresent()) {
            return new ResponseEntity("Locacao não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(locacao.map(LocacaoDTO::create));
    }
}
