package com.example.locadora.api.dto;


import com.example.locadora.model.entity.aluguel.Reserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReservaDTO {

    private Long id;
    private Long idCliente;
    private Long idLojaRetirada;
    private LocalDateTime dataHoraProgramada;
    private Long idLojaProgramada;
    private LocalDateTime dataHoraRetirada;
    private Long idCategoria;
    private Long idTipoAluguel;
    private Float valorReserva;

    public static ReservaDTO create(Reserva reserva){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reserva, ReservaDTO.class);
    }
}
