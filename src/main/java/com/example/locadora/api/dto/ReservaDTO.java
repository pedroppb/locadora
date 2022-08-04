package com.example.locadora.api.dto;


import com.example.locadora.model.entity.aluguel.Reserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReservaDTO {

    private Long id;
    private Long idCliente;
    private Long idLojaRetirada;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHoraProgramada;
    private Long idLojaProgramada;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHoraRetirada;
    private Long idCategoria;
    private Long idTipoAluguel;
    private Float valorReserva;

    public static ReservaDTO create(Reserva reserva){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reserva, ReservaDTO.class);
    }
}
