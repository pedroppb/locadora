package com.example.locadora.api.controller;


import com.example.locadora.model.entity.aluguel.Locacao;
import com.example.locadora.api.dto.LocacaoDTO;
import com.example.locadora.service.aluguel.LocacaoService;


import com.example.locadora.model.entity.aluguel.Reserva;
import com.example.locadora.service.aluguel.ReservaService;
import com.example.locadora.model.entity.pessoa.Cliente;
import com.example.locadora.service.pessoa.ClienteService;
import com.example.locadora.model.entity.aluguel.Loja;
import com.example.locadora.service.aluguel.LojaService;
import com.example.locadora.model.entity.pessoa.Funcionario;
import com.example.locadora.service.pessoa.FuncionarioService;
import com.example.locadora.model.entity.carro.Carro;
import com.example.locadora.service.carro.CarroService;
import com.example.locadora.model.entity.carro.TipoAluguel;
import com.example.locadora.service.carro.TipoAluguelService;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/locacoes")
@RequiredArgsConstructor

public class LocacaoController {
    private final LocacaoService service;
    private final ReservaService reservaService;
    private final ClienteService clienteService;
    private final LojaService lojaService;
    private final CarroService carroService;
    private final TipoAluguelService tipoAluguelService;
    private final FuncionarioService funcionarioService;

    @GetMapping()
    public ResponseEntity get() {
        List<Locacao> locacoes = service.getLocacoes();
        return ResponseEntity.ok(locacoes.stream().map(LocacaoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Locacao> locacao = service.getLocacaoById(id);
        if (!locacao.isPresent()) {
            return new ResponseEntity("Locacao não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(locacao.map(LocacaoDTO::create));
    }
    @PostMapping()
    public ResponseEntity post(LocacaoDTO dto) {
        try {
            Locacao locacao = converter(dto);
            locacao = service.salvar(locacao);
            return new ResponseEntity(locacao, HttpStatus.CREATED);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, LocacaoDTO dto) {
        if (!service.getLocacaoById(id).isPresent()) {
            return new ResponseEntity("Locacao não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Locacao locacao = converter(dto);
            locacao.setId(id);
            service.salvar(locacao);
            return ResponseEntity.ok(locacao);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Locacao> locacao = service.getLocacaoById(id);
        if (!locacao.isPresent()) {
            return new ResponseEntity("Locacao não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(locacao.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Locacao converter(LocacaoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Locacao locacao = modelMapper.map(dto, Locacao.class);

        if (dto.getIdReserva() != null) {
            Optional<Reserva> reserva = reservaService.getReservaById(dto.getIdReserva());
            if (!reserva.isPresent()) {
                locacao.setReserva(null);
            } else {
                locacao.setReserva(reserva.get());
            }
        }
        if (dto.getIdCliente() != null) {
            Optional<Cliente> cliente = clienteService.getClienteById(dto.getIdCliente());
            if (!cliente.isPresent()) {
                locacao.setCliente(null);
            } else {
                locacao.setCliente(cliente.get());
            }
        }
        if (dto.getIdCarro() != null) {
            Optional<Carro> carro = carroService.getCarroById(dto.getIdCarro());
            if (!carro.isPresent()) {
                locacao.setCarro(null);
            } else {
                locacao.setCarro(carro.get());
            }
        }
        if (dto.getIdLojaRetirada() != null) {
            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLojaRetirada());
            if (!loja.isPresent()) {
                locacao.setLojaRetirada(null);
            } else {
                locacao.setLojaRetirada(loja.get());
            }
        }
        if (dto.getIdFuncionarioRetirada() != null) {
            Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(dto.getIdFuncionarioRetirada());
            if (!funcionario.isPresent()) {
                locacao.setFuncionarioRetirada(null);
            } else {
                locacao.setFuncionarioRetirada(funcionario.get());
            }
        }
        if (dto.getIdLojaProgramada() != null) {
            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLojaProgramada());
            if (!loja.isPresent()) {
                locacao.setLojaProgramada(null);
            } else {
                locacao.setLojaProgramada(loja.get());
            }
        }
        if (dto.getIdLojaDevolucao() != null) {
            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLojaDevolucao());
            if (!loja.isPresent()) {
                locacao.setLojaDevolucao(null);
            } else {
                locacao.setLojaDevolucao(loja.get());
            }
        }
        if (dto.getIdFuncionarioDevolucao() != null) {
            Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(dto.getIdFuncionarioDevolucao());
            if (!funcionario.isPresent()) {
                locacao.setFuncionarioDevolucao(null);
            } else {
                locacao.setFuncionarioDevolucao(funcionario.get());
            }
        }
        if (dto.getIdTipoAluguel() != null) {
            Optional<TipoAluguel> tipoAluguel = tipoAluguelService.getTipoAluguelById(dto.getIdTipoAluguel());
            if (!tipoAluguel.isPresent()) {
                locacao.setTipoAluguel(null);
            } else {
                locacao.setTipoAluguel(tipoAluguel.get());
            }
        }

        return locacao;
    }
}
