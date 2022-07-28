package com.example.locadora.api.controller;



import com.example.locadora.model.entity.pessoa.Funcionario;
import com.example.locadora.service.pessoa.FuncionarioService;
import com.example.locadora.api.dto.FuncionarioDTO;

import com.example.locadora.model.entity.pessoa.Cargo;
import com.example.locadora.service.pessoa.CargoService;
import com.example.locadora.model.entity.aluguel.Loja;
import com.example.locadora.service.aluguel.LojaService;
import com.example.locadora.model.entity.pessoa.Telefone;
import com.example.locadora.service.pessoa.TelefoneService;

import com.example.locadora.model.entity.pessoa.endereco.Endereco;
import com.example.locadora.service.pessoa.endereco.EnderecoService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/funcionarios")
@RequiredArgsConstructor

public class FuncionarioController {
    private final FuncionarioService service;
    private final EnderecoService enderecoService;
    private final TelefoneService telefoneService;
    private final LojaService lojaService;
    private final CargoService cargoService;

    @GetMapping("/get")
    public ResponseEntity get() {
        List<Funcionario> funcionarios = service.getFuncionarios();
        return ResponseEntity.ok(funcionarios.stream().map(FuncionarioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = service.getFuncionarioById(id);
        if (!funcionario.isPresent()) {
            return new ResponseEntity("Funcionario não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(funcionario.map(FuncionarioDTO::create));
    }

    @PostMapping("/post")
    public ResponseEntity post(FuncionarioDTO dto) {
        try {
            Funcionario funcionario = converter(dto);
            funcionario = service.salvar(funcionario);
            return new ResponseEntity(funcionario, HttpStatus.CREATED);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("put/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, FuncionarioDTO dto) {
        if (!service.getFuncionarioById(id).isPresent()) {
            return new ResponseEntity("Funcionario não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Funcionario funcionario = converter(dto);
            funcionario.setId(id);
            service.salvar(funcionario);
            return ResponseEntity.ok(funcionario);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = service.getFuncionarioById(id);
        if (!funcionario.isPresent()) {
            return new ResponseEntity("Funcionario não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(funcionario.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Funcionario converter(FuncionarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Funcionario funcionario = modelMapper.map(dto, Funcionario.class);

        if (dto.getIdEndereco() != null) {
            Optional<Endereco> endereco = enderecoService.getEnderecoById(dto.getIdEndereco());
            if (!endereco.isPresent()) {
                funcionario.setEndereco(null);
            } else {
                funcionario.setEndereco(endereco.get());
            }
        }

        if (dto.getIdFixo() != null) {
            Optional<Telefone> telefone = telefoneService.getTelefoneById(dto.getIdFixo());
            if (!telefone.isPresent()) {
                funcionario.setFixo(null);
            } else {
                funcionario.setFixo(telefone.get());
            }
        }

        if (dto.getIdCelular() != null) {
            Optional<Telefone> telefone = telefoneService.getTelefoneById(dto.getIdCelular());
            if (!telefone.isPresent()) {
                funcionario.setCelular(null);
            } else {
                funcionario.setCelular(telefone.get());
            }
        }

        if (dto.getIdCargo() != null) {
            Optional<Cargo> cargo = cargoService.getCargoById(dto.getIdCargo());
            if (!cargo.isPresent()) {
                funcionario.setCargo(null);
            } else {
                funcionario.setCargo(cargo.get());
            }
        }
        if (dto.getIdLoja() != null) {
            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLoja());
            if (!loja.isPresent()) {
                funcionario.setLoja(null);
            } else {
                funcionario.setLoja(loja.get());
            }
        }
        return funcionario;
    }
}
