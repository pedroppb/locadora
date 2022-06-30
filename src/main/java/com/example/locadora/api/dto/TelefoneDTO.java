package com.example.locadora.api.dto;

import com.example.locadora.model.entity.pessoa.Telefone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TelefoneDTO {

    private Long id;
    private String tipo;
    private String ddd;
    private String numero;

    public static TelefoneDTO create(Telefone telefone){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(telefone, TelefoneDTO.class);
    }
}

