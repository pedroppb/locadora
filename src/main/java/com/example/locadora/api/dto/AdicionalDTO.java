package com.example.locadora.api.dto;

import com.example.locadora.model.entity.aluguel.Adicional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdicionalDTO {

    private Long id;
    private String nome;
    private float valor;

    public  static AdicionalDTO create(Adicional adicional){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(adicional, AdicionalDTO.class);
    }
}

