package com.example.locadora.api.dto;

import com.example.locadora.model.entity.aluguel.AdicionalReserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdicionalReservaDTO {
    private Long id;
    private Long idAdicional;
    private Long idReserva;
    private float valor;

    public static AdicionalReservaDTO create(AdicionalReserva adicionalReserva){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(adicionalReserva, AdicionalReservaDTO.class);
    }
}
