package com.example.locadora.api.dto;

import com.example.locadora.model.entity.carro.TipoAluguel;
import org.modelmapper.ModelMapper;

public class TipoAluguelDTO {

    private Long id;
    private String nome;
    private String descricao;

    public  static TipoAluguelDTO create(TipoAluguel tipoAluguel){
        ModelMapper modelMapper = new ModelMapper();
        TipoAluguelDTO dto = modelMapper.map(tipoAluguel, TipoAluguelDTO.class);

        return dto;
    }
}
