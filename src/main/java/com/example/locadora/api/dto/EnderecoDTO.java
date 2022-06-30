package com.example.locadora.api.dto;

import com.example.locadora.model.entity.pessoa.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EnderecoDTO {

    private Long id;

    private String cep;
    private String rua;
    private String bairro;
    private Long idCidade;

    public static EnderecoDTO create(Endereco endereco){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(endereco, EnderecoDTO.class);
    }
}
