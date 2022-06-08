package com.example.locadora.api.dto;

import com.example.locadora.model.entity.carro.MarcaCarro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MarcaCarroDTO {

    private Long id;
    private String nome;

    public static MarcaCarroDTO create(MarcaCarro marcaCarro){
        ModelMapper modelMapper = new ModelMapper();
        MarcaCarroDTO dto = modelMapper.map(marcaCarro, MarcaCarroDTO.class);

        return dto;
    }
}
