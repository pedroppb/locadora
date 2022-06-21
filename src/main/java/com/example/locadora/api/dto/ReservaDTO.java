package com.example.locadora.api.dto;


import com.example.locadora.model.entity.aluguel.Reserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReservaDTO {

    private Long id;
    private Long idCliente;
    private Long idLojaRetirada;
    private Long idLojaProgramada;
    private LocalDate dataHoraProgramada;
    private LocalTime horaHoraRetirada;
    private Long idCategoria;
    private Long idTipoAluguel;
    private Float valorReserva;

    public static ReservaDTO create(Reserva reserva){
        ModelMapper modelMapper = new ModelMapper();
        ReservaDTO dto = modelMapper.map(reserva, ReservaDTO.class);

        //dto.valorReserva = reserva.setTipoAluguel();
        return dto;
    }
}
