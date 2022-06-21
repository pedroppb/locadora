package com.example.locadora.api.controller;

        import com.example.locadora.api.dto.TipoExtraDTO;
        import com.example.locadora.model.entity.aluguel.extra.TipoExtra;
        import com.example.locadora.service.aluguel.extra.TipoExtraService;

        import lombok.RequiredArgsConstructor;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;
        import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/TipoExtras")
@RequiredArgsConstructor
public class TipoExtraController {

    private final TipoExtraService service;

    @GetMapping()
    public ResponseEntity get() {
        List<TipoExtra> tipoExtras = service.getTipoAluguel();
        return ResponseEntity.ok(tipoExtras.stream().map(TipoExtraDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<TipoExtra> tipoExtra = service.getTipoExtraById(id);
        if (!tipoExtra.isPresent()) {
            return new ResponseEntity("TipoExtra não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(tipoExtra.map(TipoExtraDTO::create));
    }
}