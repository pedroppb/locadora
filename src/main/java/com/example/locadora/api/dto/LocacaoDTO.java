package com.example.locadora.api.dto;

import com.example.locadora.model.entity.aluguel.Locacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LocacaoDTO {

    private Long id;
    private Long idReserva;
    private Long idCliente;
    private Long idCarro;

    private Long idLojaRetirada;
    private LocalDateTime dataHoraRetirada;
    private Long idFuncionarioRetirada;

    private Long idLojaProgramada;
    private LocalDateTime dataHoraProgramada;


    private Long idLojaDevolucao;
    private LocalDateTime dataHoraDevolucao;
    private Long idFuncionarioDevolucao;

    private Long idTipoAluguel;
    private float odometroRetirada;
    private float odometroDevolucao;
    private float valor;

    public static LocacaoDTO create(Locacao locacao){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(locacao, LocacaoDTO.class);
    }

}
