package com.example.locadora.api.controller;

        import com.example.locadora.model.entity.pessoa.endereco.Estado;
        import com.example.locadora.service.pessoa.endereco.EstadoService;
        import com.example.locadora.model.entity.pessoa.endereco.Cidade;
        import com.example.locadora.service.pessoa.endereco.CidadeService;
        import com.example.locadora.api.dto.CidadeDTO;


        import lombok.RequiredArgsConstructor;
        import org.modelmapper.ModelMapper;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;
        import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cidades")
@RequiredArgsConstructor

public class CidadeController {

    private final CidadeService service;
    private final EstadoService paisService;


    @GetMapping("/get")
    public ResponseEntity get() {
        List<Cidade> cidades = service.getCidades();
        return ResponseEntity.ok(cidades.stream().map(CidadeDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = service.getCidadeById(id);
        if (!cidade.isPresent()) {
            return new ResponseEntity("Cidade não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cidade.map(CidadeDTO::create));
    }

    @PostMapping("/post")
    public ResponseEntity post(CidadeDTO dto) {
        try {
            Cidade cidade = converter(dto);
            cidade = service.salvar(cidade);
            return new ResponseEntity(cidade, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/put/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, CidadeDTO dto) {
        if (!service.getCidadeById(id).isPresent()) {
            return new ResponseEntity("Cidade não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Cidade cidade = converter(dto);
            cidade.setId(id);
            service.salvar(cidade);
            return ResponseEntity.ok(cidade);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = service.getCidadeById(id);
        if (!cidade.isPresent()) {
            return new ResponseEntity("Cidade não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(cidade.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Cidade converter(CidadeDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Cidade cidade = modelMapper.map(dto, Cidade.class);
        if (dto.getIdEstado() != null) {
            Optional<Estado> estado = paisService.getEstadoById(dto.getIdEstado());
            if (!estado.isPresent()) {
                cidade.setEstado(null);
            } else {
                cidade.setEstado(estado.get());
            }
        }

        return cidade;
    }

}
