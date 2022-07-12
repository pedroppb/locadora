package com.example.locadora.api.controller;

import com.example.locadora.model.entity.carro.Carro;
import com.example.locadora.service.carro.CarroService;
import com.example.locadora.model.entity.carro.Modelo;
import com.example.locadora.service.carro.ModeloService;
import com.example.locadora.model.entity.carro.Categoria;
import com.example.locadora.service.carro.CategoriaService;
import com.example.locadora.model.entity.aluguel.Loja;
import com.example.locadora.service.aluguel.LojaService;
import com.example.locadora.api.dto.CarroDTO;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/carros")
@RequiredArgsConstructor

public class CarroController {
    private final CarroService service;
    private final ModeloService modeloService;
    private final CategoriaService categoriaService;
    private final LojaService lojaService;

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

    @PostMapping()
    public ResponseEntity post(CarroDTO dto) {
        try {
            Carro carro = converter(dto);
            carro = service.salvar(carro);
            return new ResponseEntity(carro, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, CarroDTO dto) {
        if (!service.getCarroById(id).isPresent()) {
            return new ResponseEntity("Carro não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Carro carro = converter(dto);
            carro.setId(id);
            service.salvar(carro);
            return ResponseEntity.ok(carro);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Carro> carro = service.getCarroById(id);
        if (!carro.isPresent()) {
            return new ResponseEntity("Carro não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(carro.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Carro converter(CarroDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Carro carro = modelMapper.map(dto, Carro.class);
        if (dto.getIdModelo() != null) {
            Optional<Modelo> modelo = modeloService.getModeloById(dto.getIdModelo());
            if (!modelo.isPresent()) {
                carro.setModelo(null);
            } else {
                carro.setModelo(modelo.get());
            }
        }
        if (dto.getIdCategoria() != null) {
            Optional<Categoria> categoria = categoriaService.getCategoriaById(dto.getIdCategoria());
            if (!categoria.isPresent()) {
                carro.setCategoria(null);
            } else {
                carro.setCategoria(categoria.get());
            }
        }
        if (dto.getIdLoja() != null) {
            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLoja());
            if (!loja.isPresent()) {
                carro.setLoja(null);
            } else {
                carro.setLoja(loja.get());
            }
        }

        return carro;
    }

}
