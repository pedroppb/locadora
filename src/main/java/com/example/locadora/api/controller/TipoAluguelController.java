package com.example.locadora.api.controller;

        import com.example.locadora.api.dto.TipoAluguelDTO;
        import com.example.locadora.model.entity.carro.TipoAluguel;
        import com.example.locadora.service.carro.TipoAluguelService;
        import lombok.RequiredArgsConstructor;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;
        import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/TipoAlugueis")
@RequiredArgsConstructor
public class  TipoAluguelController {

    private final TipoAluguelService service;

    @GetMapping()
    public ResponseEntity get() {
        List<TipoAluguel> TipoAlugueis = service.getTipoAlugueis();
        return ResponseEntity.ok(TipoAlugueis.stream().map(TipoAluguelDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<TipoAluguel> tipoAluguel = service.geTipoAluguelById(id);
        if (!tipoAluguel.isPresent()) {
            return new ResponseEntity("TipoAluguel não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(tipoAluguel.map(TipoAluguelDTO::create));
    }
}