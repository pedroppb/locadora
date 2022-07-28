package com.example.locadora.api.controller;

import com.example.locadora.model.entity.aluguel.extra.TipoExtra;
import com.example.locadora.service.aluguel.extra.TipoExtraService;
import com.example.locadora.api.dto.TipoExtraDTO;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tiposExtra")
@RequiredArgsConstructor

public class TipoExtraController {

    private final TipoExtraService service;


    @GetMapping("/get")
    public ResponseEntity get() {
        List<TipoExtra> tiposExtras = service.getTiposExtra();
        return ResponseEntity.ok(tiposExtras.stream().map(TipoExtraDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<TipoExtra> tipoExtra = service.getTipoExtraById(id);
        if (!tipoExtra.isPresent()) {
            return new ResponseEntity("TipoAluguel não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(tipoExtra.map(TipoExtraDTO::create));
    }
    @PostMapping("/post")
    public ResponseEntity post(TipoExtraDTO dto) {
        try {
            TipoExtra tipoExtra = converter(dto);
            tipoExtra = service.salvar(tipoExtra);
            return new ResponseEntity(tipoExtra, HttpStatus.CREATED);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, TipoExtraDTO dto) {
        if (!service.getTipoExtraById(id).isPresent()) {
            return new ResponseEntity("TipoExtra não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            TipoExtra tipoExtra = converter(dto);
            tipoExtra.setId(id);
            service.salvar(tipoExtra);
            return ResponseEntity.ok(tipoExtra);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<TipoExtra> tipoExtra = service.getTipoExtraById(id);
        if (!tipoExtra.isPresent()) {
            return new ResponseEntity("TipoExtra não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(tipoExtra.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public TipoExtra converter(TipoExtraDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, TipoExtra.class);
    }
}
