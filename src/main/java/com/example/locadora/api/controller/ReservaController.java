package com.example.locadora.api.controller;

import com.example.locadora.model.entity.aluguel.Reserva;
import com.example.locadora.api.dto.ReservaDTO;
import com.example.locadora.service.aluguel.ReservaService;


import com.example.locadora.model.entity.pessoa.Cliente;
import com.example.locadora.service.pessoa.ClienteService;
import com.example.locadora.model.entity.aluguel.Loja;
import com.example.locadora.service.aluguel.LojaService;
import com.example.locadora.model.entity.carro.Categoria;
import com.example.locadora.service.carro.CategoriaService;
import com.example.locadora.model.entity.carro.TipoAluguel;
import com.example.locadora.service.carro.TipoAluguelService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
        private final ClienteService clienteService;
        private final LojaService lojaService;
        private final CategoriaService categoriaService;
        private final TipoAluguelService tipoAluguelService;

        @GetMapping("/get")
        public ResponseEntity get() {
            List<Reserva> reservas = service.getReservas();
            return ResponseEntity.ok(reservas.stream().map(ReservaDTO::create).collect(Collectors.toList()));
        }

        @GetMapping("/get/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Reserva> reserva = service.getReservaById(id);
            if (!reserva.isPresent()) {
                return new ResponseEntity("Reserva não encontrado", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(reserva.map(ReservaDTO::create));
        }
    @PostMapping("/post")
    public ResponseEntity post(ReservaDTO dto) {
        try {
            Reserva reserva = converter(dto);
            reserva = service.salvar(reserva);
            return new ResponseEntity(reserva, HttpStatus.CREATED);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, ReservaDTO dto) {
        if (!service.getReservaById(id).isPresent()) {
            return new ResponseEntity("Reserva não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Reserva reserva = converter(dto);
            reserva.setId(id);
            service.salvar(reserva);
            return ResponseEntity.ok(reserva);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Reserva> reserva = service.getReservaById(id);
        if (!reserva.isPresent()) {
            return new ResponseEntity("Reserva não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(reserva.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Reserva converter(ReservaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Reserva reserva = modelMapper.map(dto, Reserva.class);

        if (dto.getIdCliente() != null) {
            Optional<Cliente> cliente = clienteService.getClienteById(dto.getIdCliente());
            if (!cliente.isPresent()) {
                reserva.setCliente(null);
            } else {
                reserva.setCliente(cliente.get());
            }
        }
        if (dto.getIdLojaRetirada() != null) {
            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLojaRetirada());
            if (!loja.isPresent()) {
                reserva.setLojaRetirada(null);
            } else {
                reserva.setLojaRetirada(loja.get());
            }
        }
        if (dto.getIdLojaProgramada() != null) {
            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLojaProgramada());
            if (!loja.isPresent()) {
                reserva.setLojaProgramada(null);
            } else {
                reserva.setLojaProgramada(loja.get());
            }
        }
        if (dto.getIdLojaProgramada() != null) {
            Optional<Categoria> categoria = categoriaService.getCategoriaById(dto.getIdCategoria());
            if (!categoria.isPresent()) {
                reserva.setCategoria(null);
            } else {
                reserva.setCategoria(categoria.get());
            }
        }

        if (dto.getIdLojaProgramada() != null) {
            Optional<TipoAluguel> tipoAluguel = tipoAluguelService.getTipoAluguelById(dto.getIdTipoAluguel());
            if (!tipoAluguel.isPresent()) {
                reserva.setTipoAluguel(null);
            } else {
                reserva.setTipoAluguel(tipoAluguel.get());
            }
        }

        return reserva;
    }
}
