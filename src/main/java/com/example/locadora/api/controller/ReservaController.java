package com.example.locadora.api.controller;

import com.example.locadora.service.aluguel.ReservaService;
import com.example.locadora.api.dto.ReservaDTO;
import com.example.locadora.model.entity.aluguel.Reserva;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/reservas")
@RequiredArgsConstructor

public class ReservaController {
        private final ReservaService service;


        @GetMapping()
        public ResponseEntity get() {
            List<Reserva> reservas = service.getReservas();
            return ResponseEntity.ok(reservas.stream().map(ReservaDTO::create).collect(Collectors.toList()));
        }

        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Reserva> reserva = service.getReservaById(id);
            if (!reserva.isPresent()) {
                return new ResponseEntity("Reserva não encontrado", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(reserva.map(ReservaDTO::create));
        }
}
