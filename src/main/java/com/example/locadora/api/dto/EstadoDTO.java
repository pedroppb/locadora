package com.example.locadora.api.dto;

import com.example.locadora.model.entity.pessoa.endereco.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EstadoDTO {

    private Long id;
    private String nome;
    private Long idPais;

    public static EstadoDTO create(Estado estado){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(estado, EstadoDTO.class);
    }
}
