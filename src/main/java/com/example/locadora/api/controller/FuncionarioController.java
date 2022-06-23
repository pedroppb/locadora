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
@RequestMapping("/api/v1/funcionarios")
@RequiredArgsConstructor

public class FuncionarioController {
    private final FuncionarioService service;
    private final EnderecoService enderecoService;
    private final CidadeService cidadeService;
    private final EstadoService estadoService;
    private final PaisService paisService;
    private final TelefoneService telefoneService;
    private final LojaService lojaService;
    private final CargoService cargoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Funcionario> funcionarios = service.getFuncionarios();
        return ResponseEntity.ok(funcionarios.stream().map(FuncionarioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = service.getFuncionarioById(id);
        if (!funcionario.isPresent()) {
            return new ResponseEntity("Funcionario não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(funcionario.map(FuncionarioDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(FuncionarioDTO dto) {
        try {
            Funcionario funcionario = converter(dto);
            funcionario = salvar(funcionario);
            funcionario = service.salvar(funcionario);
            return new ResponseEntity(funcionario, HttpStatus.CREATED);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, FuncionarioDTO dto) {
        if (!service.getFuncionarioById(id).isPresent()) {
            return new ResponseEntity("Funcionario não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Funcionario funcionario = converter(dto);
            funcionario.setId(id);
            funcionario = salvar(funcionario);
            service.salvar(funcionario);
            return ResponseEntity.ok(funcionario);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
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
        funcionario.setEndereco(endereco);

        Telefone fixo = new Telefone();
        fixo.setTipo(dto.getFixoTipo());
        fixo.setDdd(dto.getFixoDdd());
        fixo.setNumero(dto.getFixoNumero());
        funcionario.setFixo(fixo);

        Telefone celular = new Telefone();
        celular.setTipo(dto.getCelularTipo());
        celular.setDdd(dto.getCelularDdd());
        celular.setNumero(dto.getCelularNumero());
        funcionario.setCelular(celular);

        if (dto.getIdLoja() != null) {
            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLoja());
            if (!loja.isPresent()) {
                funcionario.setLoja(null);
            } else {
                funcionario.setLoja(loja.get());
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

        return funcionario;
    }
    public Funcionario salvar(Funcionario funcionario) {
        Pais pais = paisService.salvar(funcionario.getEndereco().getCidade().getEstado().getPais());
        Estado estado = estadoService.salvar(funcionario.getEndereco().getCidade().getEstado());
        estado.setPais(pais);
        Cidade cidade = cidadeService.salvar(funcionario.getEndereco().getCidade());
        cidade.setEstado(estado);
        Endereco endereco = enderecoService.salvar(funcionario.getEndereco());
        endereco.setCidade(cidade);
        funcionario.setEndereco(endereco);
        if(funcionario.getFixo() != null){
            Telefone fixo = telefoneService.salvar(funcionario.getFixo());
            funcionario.setFixo(fixo);
        }
        if(funcionario.getCelular() != null){
            Telefone celular = telefoneService.salvar(funcionario.getCelular());
            funcionario.setCelular(celular);
        }
        return funcionario;
    }
}
