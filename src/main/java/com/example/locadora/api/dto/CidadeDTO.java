package com.example.locadora.api.dto;

import com.example.locadora.model.entity.pessoa.endereco.Cidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CidadeDTO {

    private Long id;
    private String nome;
    private Long idEstado;

    public static CidadeDTO create(Cidade cidade){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cidade, CidadeDTO.class);
    }
}
