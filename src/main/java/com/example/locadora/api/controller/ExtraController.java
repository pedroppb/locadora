package com.example.locadora.api.controller;

import com.example.locadora.service.aluguel.extra.ExtraService;
import com.example.locadora.api.dto.ExtraDTO;
import com.example.locadora.model.entity.aluguel.extra.Extra;

import lombok.RequiredArgsConstructor;
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


    @GetMapping()
    public ResponseEntity get() {
        List<Extra> extras = service.getExtras();
        return ResponseEntity.ok(extras.stream().map(ExtraDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Extra> extra = service.getExtraById(id);
        if (!extra.isPresent()) {
            return new ResponseEntity("Extra não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(extra.map(ExtraDTO::create));
    }
}
