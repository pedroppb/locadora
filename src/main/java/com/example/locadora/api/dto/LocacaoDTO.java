package com.example.locadora.api.dto;

import com.example.locadora.model.entity.aluguel.Locacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHoraRetirada;
    private Long idFuncionarioRetirada;

    private Long idLojaProgramada;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHoraProgramada;


    private Long idLojaDevolucao;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
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
