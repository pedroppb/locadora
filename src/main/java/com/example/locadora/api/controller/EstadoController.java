package com.example.locadora.api.controller;
    import com.example.locadora.model.entity.pessoa.endereco.Pais;
    import com.example.locadora.service.pessoa.endereco.PaisService;
    import com.example.locadora.model.entity.pessoa.endereco.Estado;
    import com.example.locadora.service.pessoa.endereco.EstadoService;
    import com.example.locadora.api.dto.EstadoDTO;


    import lombok.RequiredArgsConstructor;
    import org.modelmapper.ModelMapper;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;
    import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/estados")
@RequiredArgsConstructor

public class EstadoController {

    private final EstadoService service;
    private final PaisService paisService;


    @GetMapping()
    public ResponseEntity get() {
        List<Estado> estados = service.getEstados();
        return ResponseEntity.ok(estados.stream().map(EstadoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Estado> estado = service.getEstadoById(id);
        if (!estado.isPresent()) {
            return new ResponseEntity("Estado não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(estado.map(EstadoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(EstadoDTO dto) {
        try {
            Estado estado = converter(dto);
            estado = service.salvar(estado);
            return new ResponseEntity(estado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, EstadoDTO dto) {
        if (!service.getEstadoById(id).isPresent()) {
            return new ResponseEntity("Estado não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Estado estado = converter(dto);
            estado.setId(id);
            service.salvar(estado);
            return ResponseEntity.ok(estado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Estado> estado = service.getEstadoById(id);
        if (!estado.isPresent()) {
            return new ResponseEntity("Estado não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(estado.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Estado converter(EstadoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Estado estado = modelMapper.map(dto, Estado.class);
        if (dto.getIdPais() != null) {
            Optional<Pais> pais = paisService.getPaisById(dto.getIdPais());
            if (!pais.isPresent()) {
                estado.setPais(null);
            } else {
                estado.setPais(pais.get());
            }
        }

        return estado;
    }

}
