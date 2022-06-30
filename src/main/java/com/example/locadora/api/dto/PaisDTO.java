package com.example.locadora.api.dto;

import com.example.locadora.model.entity.pessoa.endereco.Pais;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PaisDTO {

    private Long id;
    private String nome;

    public static ModeloDTO create(Pais pais){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pais, ModeloDTO.class);
    }
}
