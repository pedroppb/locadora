package com.example.locadora.api.controller;

import com.example.locadora.api.dto.ClienteDTO;
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
    private final CidadeService cidadeService;
    private final EstadoService estadoService;
    private final PaisService paisService;
    private final TelefoneService telefoneService;
    @GetMapping()
    public ResponseEntity get() {
        List<Cliente> clientes = service.getClientes();
        return ResponseEntity.ok(clientes.stream().map(ClienteDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = service.getClienteById(id);
        if (!cliente.isPresent()) {
            return new ResponseEntity("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cliente.map(ClienteDTO::create));
    }
    @PostMapping()
    public ResponseEntity post(ClienteDTO dto) {
        try {
            Cliente cliente = converter(dto);
            cliente = salvar(cliente);
            cliente = service.salvar(cliente);
            return new ResponseEntity(cliente, HttpStatus.CREATED);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, ClienteDTO dto) {
        if (!service.getClienteById(id).isPresent()) {
            return new ResponseEntity("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Cliente cliente = converter(dto);
            cliente.setId(id);
            cliente = salvar(cliente);
            cliente = service.salvar(cliente);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
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

        Pais pais = new Pais();
        pais.setNome(dto.getNomePais());
        Estado estado = new Estado();
        estado.setNome(dto.getNomeEstado());
        estado.setPais(pais);
        Cidade cidade = new Cidade();
        cidade.setNome(dto.getNomeCidade());
        cidade.setEstado(estado);
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        endereco.setCidade(cidade);
        cliente.setEndereco(endereco);

        Telefone fixo = new Telefone();
        fixo.setTipo(dto.getFixoTipo());
        fixo.setDdd(dto.getFixoDdd());
        fixo.setNumero(dto.getFixoNumero());
        cliente.setFixo(fixo);

        Telefone celular = new Telefone();
        celular.setTipo(dto.getCelularTipo());
        celular.setDdd(dto.getCelularDdd());
        celular.setNumero(dto.getCelularNumero());
        cliente.setCelular(celular);

        Telefone outro = new Telefone();
        outro.setTipo(dto.getOutroTipo());
        outro.setDdd(dto.getOutroDdd());
        outro.setNumero(dto.getOutroNumero());
        cliente.setOutro(outro);

        return cliente;
    }
    public Cliente salvar(Cliente cliente) {
        Pais pais = paisService.salvar(cliente.getEndereco().getCidade().getEstado().getPais());
        Estado estado = estadoService.salvar(cliente.getEndereco().getCidade().getEstado());
        estado.setPais(pais);
        Cidade cidade = cidadeService.salvar(cliente.getEndereco().getCidade());
        cidade.setEstado(estado);
        Endereco endereco = enderecoService.salvar(cliente.getEndereco());
        endereco.setCidade(cidade);
        cliente.setEndereco(endereco);
        if(cliente.getFixo() != null){
            Telefone fixo = telefoneService.salvar(cliente.getFixo());
            cliente.setFixo(fixo);
        }
        if(cliente.getCelular() != null){
            Telefone celular = telefoneService.salvar(cliente.getCelular());
            cliente.setCelular(celular);
        }
        if(cliente.getOutro() != null){
            Telefone outro = telefoneService.salvar(cliente.getOutro());
            cliente.setOutro(outro);
        }
        return cliente;
    }
}
