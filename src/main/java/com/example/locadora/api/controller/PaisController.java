package com.example.locadora.api.controller;

        import com.example.locadora.model.entity.pessoa.endereco.Pais;
        import com.example.locadora.service.pessoa.endereco.PaisService;
        import com.example.locadora.api.dto.PaisDTO;

        import lombok.RequiredArgsConstructor;
        import org.modelmapper.ModelMapper;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;
        import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/paises")
@RequiredArgsConstructor

public class PaisController {

    private final PaisService service;


    @GetMapping("/get")
    public ResponseEntity get() {
        List<Pais> paises = service.getPaises();
        return ResponseEntity.ok(paises.stream().map(PaisDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Pais> pais = service.getPaisById(id);
        if (!pais.isPresent()) {
            return new ResponseEntity("Pais não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(pais.map(PaisDTO::create));
    }

    @PostMapping("/post")
    public ResponseEntity post(PaisDTO dto) {
        try {
            Pais pais = converter(dto);
            pais = service.salvar(pais);
            return new ResponseEntity(pais, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, PaisDTO dto) {
        if (!service.getPaisById(id).isPresent()) {
            return new ResponseEntity("Pais não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Pais pais = converter(dto);
            pais.setId(id);
            service.salvar(pais);
            return ResponseEntity.ok(pais);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Pais> pais = service.getPaisById(id);
        if (!pais.isPresent()) {
            return new ResponseEntity("Pais não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(pais.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Pais converter(PaisDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Pais.class);
    }
}
