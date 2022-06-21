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

    private Long idReserva;

    private Long idCliente;

    private Long idCarro;

    private Long idLojaRetirada;

    private Long idFuncionarioRetirada;

    private Long idLojaProgramada;

    private LocalDate dataHoraProgramada;
    private LocalTime horaHoraRetirada;

    private Long idLojaDevolucao;

    private LocalDate dataDevolucao;
    private LocalTime horaDevolucao;

    private Long idFuncionarioDevolucao;

    private Long idTipoAluguel;

    private float valor;

    private LocalDate dataVencimento;
    private LocalDate dataPagamento;

    public static LocacaoDTO create(Locacao locacao){
        ModelMapper modelMapper = new ModelMapper();
        LocacaoDTO dto = modelMapper.map(locacao, LocacaoDTO.class);
        return dto;
    }

}
