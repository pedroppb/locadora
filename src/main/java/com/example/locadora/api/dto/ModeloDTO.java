package com.example.locadora.api.dto;

import com.example.locadora.model.entity.carro.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ModeloDTO {

    private Long id;
    private String nome;
    private long idMarcaCarro;

    public static ModeloDTO create(Modelo modelo){
        ModelMapper modelMapper = new ModelMapper();
        ModeloDTO dto = modelMapper.map(modelo, ModeloDTO.class);
        return dto;
    }
}
