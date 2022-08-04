package com.example.locadora.api.controller;
        import com.example.locadora.model.entity.pessoa.endereco.Cidade;
        import com.example.locadora.service.pessoa.endereco.CidadeService;
        import com.example.locadora.model.entity.pessoa.endereco.Endereco;
        import com.example.locadora.service.pessoa.endereco.EnderecoService;
        import com.example.locadora.api.dto.EnderecoDTO;


        import lombok.RequiredArgsConstructor;
        import org.modelmapper.ModelMapper;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;
        import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/enderecos")
@RequiredArgsConstructor

public class EnderecoController {

    private final EnderecoService service;
    private final CidadeService cidadeService;


    @GetMapping("/get")
    public ResponseEntity get() {
        List<Endereco> enderecos = service.getEnderecos();
        return ResponseEntity.ok(enderecos.stream().map(EnderecoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Endereco> endereco = service.getEnderecoById(id);
        if (!endereco.isPresent()) {
            return new ResponseEntity("Endereco não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(endereco.map(EnderecoDTO::create));
    }

    @PostMapping("/post")
    public ResponseEntity post(EnderecoDTO dto) {
        try {
            Endereco endereco = converter(dto);
            endereco = service.salvar(endereco);
            return new ResponseEntity(endereco, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/put/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, EnderecoDTO dto) {
        if (!service.getEnderecoById(id).isPresent()) {
            return new ResponseEntity("Endereco não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Endereco endereco = converter(dto);
            endereco.setId(id);
            service.salvar(endereco);
            return ResponseEntity.ok(endereco);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Endereco> endereco = service.getEnderecoById(id);
        if (!endereco.isPresent()) {
            return new ResponseEntity("Endereco não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(endereco.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Endereco converter(EnderecoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        if (dto.getIdCidade() != null) {
            Optional<Cidade> cidade = cidadeService.getCidadeById(dto.getIdCidade());
            if (!cidade.isPresent()) {
                endereco.setCidade(null);
            } else {
                endereco.setCidade(cidade.get());
            }
        }
        return endereco;
    }

}
