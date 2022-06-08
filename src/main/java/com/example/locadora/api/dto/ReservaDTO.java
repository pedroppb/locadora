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
    private Integer idCliente;
    private Integer idLojaRetirada;
    private LocalDate dataRetirada;
    private LocalTime horaRetirada;
    private Integer idLojaProgramada;
    private LocalDate dataProgramada;
    private LocalTime horaProgramada;
    private Integer idCategoria;
    private Integer idTipoAluguel;

    public static ReservaDTO create(Reserva reserva){
        ModelMapper modelMapper = new ModelMapper();
        ReservaDTO dto = modelMapper.map(reserva, ReservaDTO.class);

        return dto;
    }
}
