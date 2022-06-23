package com.example.locadora.api.controller;


import com.example.locadora.model.entity.aluguel.Loja;
import com.example.locadora.service.aluguel.LojaService;
import com.example.locadora.api.dto.LojaDTO;

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
@RequestMapping("/api/v1/lojas")
@RequiredArgsConstructor

public class LojaController {
    private final LojaService service;
    private final EnderecoService enderecoService;
    private final CidadeService cidadeService;
    private final EstadoService estadoService;
    private final PaisService paisService;
    private final TelefoneService telefoneService;

    @GetMapping()
    public ResponseEntity get() {
        List<Loja> lojas = service.getLojas();
        return ResponseEntity.ok(lojas.stream().map(LojaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Loja> loja = service.getLojaById(id);
        if (!loja.isPresent()) {
            return new ResponseEntity("Loja não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(loja.map(LojaDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(LojaDTO dto) {
        try {
            Loja loja = converter(dto);
            loja= salvar(loja);
            loja = service.salvar(loja);
            return new ResponseEntity(loja, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, LojaDTO dto) {
        if (!service.getLojaById(id).isPresent()) {
            return new ResponseEntity("Loja não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Loja loja = converter(dto);
            loja.setId(id);
            loja= salvar(loja);
            service.salvar(loja);
            return ResponseEntity.ok(loja);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Loja> loja = service.getLojaById(id);
        if (!loja.isPresent()) {
            return new ResponseEntity("Loja não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(loja.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Loja converter(LojaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Loja loja = modelMapper.map(dto, Loja.class);
        Telefone telefone = new Telefone();
        telefone.setTipo(dto.getFixoTipo());
        telefone.setDdd(dto.getFixoDdd());
        telefone.setNumero(dto.getFixoNumero());
        loja.setTelefone(telefone);
        return loja;
    }
    public Loja salvar(Loja loja) {
        Pais pais = paisService.salvar(loja.getEndereco().getCidade().getEstado().getPais());
        Estado estado = estadoService.salvar(loja.getEndereco().getCidade().getEstado());
        estado.setPais(pais);
        Cidade cidade = cidadeService.salvar(loja.getEndereco().getCidade());
        cidade.setEstado(estado);
        Endereco endereco = enderecoService.salvar(loja.getEndereco());
        endereco.setCidade(cidade);
        loja.setEndereco(endereco);
        if(loja.getTelefone() != null){
            Telefone fixo = telefoneService.salvar(loja.getTelefone());
            loja.setTelefone(fixo);
        }

        return loja;
    }
}
