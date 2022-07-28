package com.example.locadora.api.controller;

import com.example.locadora.model.entity.carro.Categoria;
import com.example.locadora.service.carro.CategoriaService;
import com.example.locadora.model.entity.carro.TipoAluguel;
import com.example.locadora.service.carro.TipoAluguelService;
import com.example.locadora.model.entity.carro.Preco;
import com.example.locadora.service.carro.PrecoService;
import com.example.locadora.api.dto.PrecoDTO;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/precos")
@RequiredArgsConstructor

public class PrecoController {
    private final PrecoService service;
    private final CategoriaService categoriaService;
    private final TipoAluguelService tipoAluguelService;


    @GetMapping("/get")
    public ResponseEntity get() {
        List<Preco> precos = service.getPrecos();
        return ResponseEntity.ok(precos.stream().map(PrecoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Preco> preco = service.getPrecoById(id);
        if (!preco.isPresent()) {
            return new ResponseEntity("Preco não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(preco.map(PrecoDTO::create));
    }

    @PostMapping("/post")
    public ResponseEntity post(PrecoDTO dto) {
        try {
            Preco preco = converter(dto);
            preco = service.salvar(preco);
            return new ResponseEntity(preco, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/put/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, PrecoDTO dto) {
        if (!service.getPrecoById(id).isPresent()) {
            return new ResponseEntity("Preco não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Preco preco = converter(dto);
            preco.setId(id);
            service.salvar(preco);
            return ResponseEntity.ok(preco);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Preco> preco = service.getPrecoById(id);
        if (!preco.isPresent()) {
            return new ResponseEntity("Preco não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(preco.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Preco converter(PrecoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Preco preco = modelMapper.map(dto, Preco.class);
        if (dto.getIdCategoria() != null) {
            Optional<Categoria> categoria = categoriaService.getCategoriaById(dto.getIdCategoria());
            if (!categoria.isPresent()) {
                preco.setCategoria(null);
            } else {
                preco.setCategoria(categoria.get());
            }
        }
        if (dto.getIdTipoAluguel() != null) {
            Optional<TipoAluguel> tipoAluguel = tipoAluguelService.getTipoAluguelById(dto.getIdTipoAluguel());
            if (!tipoAluguel.isPresent()) {
                preco.setTipoAluguel(null);
            } else {
                preco.setTipoAluguel(tipoAluguel.get());
            }
        }
        return preco;
    }
}
