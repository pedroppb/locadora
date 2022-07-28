
package com.example.locadora.api.controller;

        import com.example.locadora.model.entity.pessoa.Telefone;
        import com.example.locadora.service.pessoa.TelefoneService;
        import com.example.locadora.api.dto.TelefoneDTO;

        import lombok.RequiredArgsConstructor;
        import org.modelmapper.ModelMapper;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;
        import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/telefones")
@RequiredArgsConstructor

public class TelefoneController {

    private final TelefoneService service;


    @GetMapping("/get")
    public ResponseEntity get() {
        List<Telefone> telefones = service.getTelefones();
        return ResponseEntity.ok(telefones.stream().map(TelefoneDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Telefone> telefone = service.getTelefoneById(id);
        if (!telefone.isPresent()) {
            return new ResponseEntity("Telefone não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(telefone.map(TelefoneDTO::create));
    }

    @PostMapping("/post")
    public ResponseEntity post(TelefoneDTO dto) {
        try {
            Telefone telefone = converter(dto);
            telefone = service.salvar(telefone);
            return new ResponseEntity(telefone, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, TelefoneDTO dto) {
        if (!service.getTelefoneById(id).isPresent()) {
            return new ResponseEntity("Telefone não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Telefone telefone = converter(dto);
            telefone.setId(id);
            service.salvar(telefone);
            return ResponseEntity.ok(telefone);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Telefone> telefone = service.getTelefoneById(id);
        if (!telefone.isPresent()) {
            return new ResponseEntity("Telefone não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(telefone.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Telefone converter(TelefoneDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Telefone.class);
    }
}
