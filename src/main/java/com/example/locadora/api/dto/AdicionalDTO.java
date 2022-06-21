package com.example.locadora.api.dto;

import com.example.locadora.model.entity.aluguel.Adicional;
import org.modelmapper.ModelMapper;

public class AdicionalDTO {

    private Long id;
    private String nome;
    private float valor;

    public  static AdicionalDTO create(Adicional adicional){
        ModelMapper modelMapper = new ModelMapper();
        AdicionalDTO dto = modelMapper.map(adicional, AdicionalDTO.class);

        return dto;
    }
}

