package com.example.locadora.api.controller;

import com.example.locadora.model.entity.aluguel.AdicionalLocacao;
import com.example.locadora.service.aluguel.AdicionalLocacaoService;
import com.example.locadora.api.dto.AdicionalLocacaoDTO;

import com.example.locadora.model.entity.aluguel.Adicional;
import com.example.locadora.service.aluguel.AdicionalService;
import com.example.locadora.model.entity.aluguel.Locacao;
import com.example.locadora.service.aluguel.LocacaoService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/adicionalLocacoes")
@RequiredArgsConstructor

public class AdicionalLocacaoController {
    private final AdicionalLocacaoService service;
    private final AdicionalService adicionalService;
    private final LocacaoService reservaService;


    @GetMapping("/get")
    public ResponseEntity get() {
        List<AdicionalLocacao> adicionalLocacaos = service.getAdicionaislLocacao();
        return ResponseEntity.ok(adicionalLocacaos.stream().map(AdicionalLocacaoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<AdicionalLocacao> adicionalLocacao = service.getAdicionalLocacaoById(id);
        if (!adicionalLocacao.isPresent()) {
            return new ResponseEntity("AdicionalLocacao não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adicionalLocacao.map(AdicionalLocacaoDTO::create));
    }

    @PostMapping("/post")
    public ResponseEntity post(AdicionalLocacaoDTO dto) {
        try {
            AdicionalLocacao adicionalLocacao = converter(dto);
            adicionalLocacao = service.salvar(adicionalLocacao);
            return new ResponseEntity(adicionalLocacao, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/put/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, AdicionalLocacaoDTO dto) {
        if (!service.getAdicionalLocacaoById(id).isPresent()) {
            return new ResponseEntity("AdicionalLocacao não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            AdicionalLocacao adicionalLocacao = converter(dto);
            adicionalLocacao.setId(id);
            service.salvar(adicionalLocacao);
            return ResponseEntity.ok(adicionalLocacao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<AdicionalLocacao> adicionalLocacao = service.getAdicionalLocacaoById(id);
        if (!adicionalLocacao.isPresent()) {
            return new ResponseEntity("AdicionalLocacao não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(adicionalLocacao.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public AdicionalLocacao converter(AdicionalLocacaoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        AdicionalLocacao adicionalLocacao = modelMapper.map(dto, AdicionalLocacao.class);
        if (dto.getIdAdicional() != null) {
            Optional<Adicional> adicional = adicionalService.getAdicionalById(dto.getIdAdicional());
            if (!adicional.isPresent()) {
                adicionalLocacao.setAdicional(null);
            } else {
                adicionalLocacao.setAdicional(adicional.get());
            }
        }
        if (dto.getIdLocacao() != null) {
            Optional<Locacao> locacao = reservaService.getLocacaoById(dto.getIdLocacao());
            if (!locacao.isPresent()) {
                adicionalLocacao.setLocacao(null);
            } else {
                adicionalLocacao.setLocacao(locacao.get());
            }
        }
        return adicionalLocacao;
    }
}
