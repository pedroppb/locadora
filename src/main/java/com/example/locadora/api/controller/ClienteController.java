package com.example.locadora.api.controller;

import com.example.locadora.api.dto.ClienteDTO;
import com.example.locadora.model.entity.carro.Modelo;
import com.example.locadora.model.entity.pessoa.Cliente;
import com.example.locadora.service.pessoa.ClienteService;

import com.example.locadora.model.entity.pessoa.Telefone;
import com.example.locadora.service.pessoa.TelefoneService;

import com.example.locadora.model.entity.pessoa.endereco.*;
import com.example.locadora.service.pessoa.endereco.*;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor

public class ClienteController {
    private final ClienteService service;
    private final EnderecoService enderecoService;
    private final TelefoneService telefoneService;
    @GetMapping("/get")
    public ResponseEntity get() {
        List<Cliente> clientes = service.getClientes();
        return ResponseEntity.ok(clientes.stream().map(ClienteDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = service.getClienteById(id);
        if (!cliente.isPresent()) {
            return new ResponseEntity("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cliente.map(ClienteDTO::create));
    }
    @PostMapping("/post")
    public ResponseEntity post(ClienteDTO dto) {
        try {
            Cliente cliente = converter(dto);
            cliente = service.salvar(cliente);
            return new ResponseEntity(cliente, HttpStatus.CREATED);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, ClienteDTO dto) {
        if (!service.getClienteById(id).isPresent()) {
            return new ResponseEntity("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Cliente cliente = converter(dto);
            cliente.setId(id);
            service.salvar(cliente);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = service.getClienteById(id);
        if (!cliente.isPresent()) {
            return new ResponseEntity("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(cliente.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Cliente converter(ClienteDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Cliente cliente = modelMapper.map(dto, Cliente.class);
        if (dto.getIdEndereco() != null) {
            Optional<Endereco> endereco = enderecoService.getEnderecoById(dto.getIdEndereco());
            if (!endereco.isPresent()) {
                cliente.setEndereco(null);
            } else {
                cliente.setEndereco(endereco.get());
            }
        }
        if (dto.getIdFixo() != null) {
            Optional<Telefone> telefone = telefoneService.getTelefoneById(dto.getIdFixo());
            if (!telefone.isPresent()) {
                cliente.setFixo(null);
            } else {
                cliente.setFixo(telefone.get());
            }
        }
        if (dto.getIdCelular() != null) {
            Optional<Telefone> telefone = telefoneService.getTelefoneById(dto.getIdCelular());
            if (!telefone.isPresent()) {
                cliente.setCelular(null);
            } else {
                cliente.setCelular(telefone.get());
            }
        }
        if (dto.getIdOutro() != null) {
            Optional<Telefone> telefone = telefoneService.getTelefoneById(dto.getIdOutro());
            if (!telefone.isPresent()) {
                cliente.setOutro(null);
            } else {
                cliente.setOutro(telefone.get());
            }
        }
        return cliente;
    }
}
