package com.example.locadora.api.controller;

        import com.example.locadora.service.carro.MarcaCarroService;
        import com.example.locadora.api.dto.MarcaCarroDTO;
        import com.example.locadora.model.entity.carro.MarcaCarro;

        import lombok.RequiredArgsConstructor;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;
        import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/MarcasCarro")
@RequiredArgsConstructor

public class MarcaCarroController {

    private final MarcaCarroService service;


    @GetMapping()
    public ResponseEntity get() {
        List<MarcaCarro> marcasCarro = service.getMarcasCarro();
        return ResponseEntity.ok(marcasCarro.stream().map(MarcaCarroDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<MarcaCarro> marcaCarro = service.getMarcaCarroById(id);
        if (!marcaCarro.isPresent()) {
            return new ResponseEntity("MarcaCarro não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(marcaCarro.map(MarcaCarroDTO::create));
    }
}
