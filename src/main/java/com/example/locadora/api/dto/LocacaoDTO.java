package com.example.locadora.api.dto;

import com.example.locadora.model.entity.aluguel.Locacao;
import com.example.locadora.model.entity.carro.Carro;
import com.example.locadora.model.entity.pessoa.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LocacaoDTO {

    private Long id;

    private Integer idReserva;

    private Cliente cliente;

    private Carro carro;

    private Integer idLojaRetirada;

    private LocalDate dataRetirada;
    private LocalTime horaRetirada;

    private Integer idFuncionarioRetirada;

    private Integer idLojaProgramada;

    private LocalDate dataProgramada;
    private LocalTime horaProgramada;

    private Integer idLojaDevolucao;

    private LocalDate dataDevolucao;
    private LocalTime horaDevolucao;

    private Integer idFuncionarioDevolucao;

    private Integer idTipoAluguel;

    private float valor;

    private String numero;
    private Integer tipo;
    //private float valor;

    private LocalDate dataVencimento;
    private LocalDate dataPagamento;

    public static LocacaoDTO create(Locacao locacao){
        ModelMapper modelMapper = new ModelMapper();
        LocacaoDTO dto = modelMapper.map(locacao, LocacaoDTO.class);

       // dto.numero = locacao.getCliente().getNumero();
       // dto.tipo = locacao.getCliente().getTipo();
        // dto.valor = locacao.getReserva().getId();

        return dto;
    }

}
