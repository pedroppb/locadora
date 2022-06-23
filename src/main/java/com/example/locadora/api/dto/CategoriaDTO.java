package com.example.locadora.api.dto;

import com.example.locadora.model.entity.carro.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoriaDTO {
    private Long id;
    private String nome;
    private String descricao;

    public static CategoriaDTO create(Categoria categoria){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(categoria, CategoriaDTO.class);
    }
}
