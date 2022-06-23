package com.example.locadora.api.controller;

import com.example.locadora.model.entity.aluguel.AdicionalReserva;
import com.example.locadora.service.aluguel.AdicionalReservaService;
import com.example.locadora.api.dto.AdicionalReservaDTO;

import com.example.locadora.model.entity.aluguel.Adicional;
import com.example.locadora.service.aluguel.AdicionalService;
import com.example.locadora.model.entity.aluguel.Reserva;
import com.example.locadora.service.aluguel.ReservaService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/AdicionalReservas")
@RequiredArgsConstructor

public class AdicionalReservaController {
    private final AdicionalReservaService service;
    private final AdicionalService adicionalService;
    private final ReservaService reservaService;


    @GetMapping()
    public ResponseEntity get() {
        List<AdicionalReserva> adicionalReservas = service.getAdicionaisReserva();
        return ResponseEntity.ok(adicionalReservas.stream().map(AdicionalReservaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<AdicionalReserva> adicionalReserva = service.getAdicionalReservaById(id);
        if (!adicionalReserva.isPresent()) {
            return new ResponseEntity("AdicionalReserva não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adicionalReserva.map(AdicionalReservaDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(AdicionalReservaDTO dto) {
        try {
            AdicionalReserva adicionalReserva = converter(dto);
            adicionalReserva = service.salvar(adicionalReserva);
            return new ResponseEntity(adicionalReserva, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, AdicionalReservaDTO dto) {
        if (!service.getAdicionalReservaById(id).isPresent()) {
            return new ResponseEntity("AdicionalReserva não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            AdicionalReserva adicionalReserva = converter(dto);
            adicionalReserva.setId(id);
            service.salvar(adicionalReserva);
            return ResponseEntity.ok(adicionalReserva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<AdicionalReserva> adicionalReserva = service.getAdicionalReservaById(id);
        if (!adicionalReserva.isPresent()) {
            return new ResponseEntity("AdicionalReserva não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(adicionalReserva.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public AdicionalReserva converter(AdicionalReservaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        AdicionalReserva adicionalReserva = modelMapper.map(dto, AdicionalReserva.class);
        if (dto.getIdAdicional() != null) {
            Optional<Adicional> adicional = adicionalService.getAdicionalById(dto.getIdAdicional());
            if (!adicional.isPresent()) {
                adicionalReserva.setAdicional(null);
            } else {
                adicionalReserva.setAdicional(adicional.get());
            }
        }
        if (dto.getIdReserva() != null) {
            Optional<Reserva> reserva = reservaService.getReservaById(dto.getIdReserva());
            if (!reserva.isPresent()) {
                adicionalReserva.setReserva(null);
            } else {
                adicionalReserva.setReserva(reserva.get());
            }
        }
        return adicionalReserva;
    }
}
