package com.example.locadora.api.dto;

import com.example.locadora.model.entity.pessoa.Cargo;
import org.modelmapper.ModelMapper;

public class CargoDTO {

    private Long id;
    private String nome;
    private Float salario;

    public  static CargoDTO create(Cargo cargo){
        ModelMapper modelMapper = new ModelMapper();
        CargoDTO dto = modelMapper.map(cargo, CargoDTO.class);

        return dto;
    }
}
