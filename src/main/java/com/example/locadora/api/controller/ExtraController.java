package com.example.locadora.api.controller;


import com.example.locadora.model.entity.aluguel.extra.Extra;
import com.example.locadora.service.aluguel.extra.ExtraService;
import com.example.locadora.api.dto.ExtraDTO;

import com.example.locadora.model.entity.aluguel.Locacao;
import com.example.locadora.service.aluguel.LocacaoService;
import com.example.locadora.model.entity.aluguel.extra.TipoExtra;
import com.example.locadora.service.aluguel.extra.TipoExtraService;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/extras")
@RequiredArgsConstructor


public class ExtraController {
    private final ExtraService service;
    private final TipoExtraService tipoExtraService;
    private final LocacaoService locacaoService;

    @GetMapping("/get")
    public ResponseEntity get() {
        List<Extra> extras = service.getExtras();
        return ResponseEntity.ok(extras.stream().map(ExtraDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Extra> extra = service.getExtraById(id);
        if (!extra.isPresent()) {
            return new ResponseEntity("Extra não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(extra.map(ExtraDTO::create));
    }

    @PostMapping("/post")
    public ResponseEntity post(ExtraDTO dto) {
        try {
            Extra extra = converter(dto);
            extra = service.salvar(extra);
            return new ResponseEntity(extra, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/put/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, ExtraDTO dto) {
        if (!service.getExtraById(id).isPresent()) {
            return new ResponseEntity("Extra não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Extra extra = converter(dto);
            extra.setId(id);
            service.salvar(extra);
            return ResponseEntity.ok(extra);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Extra> extra = service.getExtraById(id);
        if (!extra.isPresent()) {
            return new ResponseEntity("Extra não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(extra.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Extra converter(ExtraDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Extra extra = modelMapper.map(dto, Extra.class);
        if (dto.getIdTipoExtra() != null) {
            Optional<TipoExtra> tipoExtra = tipoExtraService.getTipoExtraById(dto.getIdTipoExtra());
            if (!tipoExtra.isPresent()) {
                extra.setTipoExtra(null);
            } else {
                extra.setTipoExtra(tipoExtra.get());
            }
        }
        if (dto.getIdLocacao() != null) {
            Optional<Locacao> locacao = locacaoService.getLocacaoById(dto.getIdLocacao());
            if (!locacao.isPresent()) {
                extra.setLocacao(null);
            } else {
                extra.setLocacao(locacao.get());
            }
        }
        return extra;
    }

}
