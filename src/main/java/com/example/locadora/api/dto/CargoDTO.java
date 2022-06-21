package com.example.locadora.api.dto;

import com.example.locadora.model.entity.pessoa.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoDTO {

    private Long id;
    private String nome;
    private Float salario;

    public  static CargoDTO create(Cargo cargo){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cargo, CargoDTO.class);
    }
}
