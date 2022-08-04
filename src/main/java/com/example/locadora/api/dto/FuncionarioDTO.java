package com.example.locadora.api.dto;

import com.example.locadora.model.entity.pessoa.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FuncionarioDTO {
    private Long id;

    private String nome;
    private String cpf;
    private String rg;
    private String email;

    private Long idFixo;

    private Long idCelular;

    private Long idEndereco;
    private String numero;
    private String complemento;
    private String pontoReferencia;

    private Long idLoja;
    private Long idCargo;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAdmissao;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDemissao;

    public static FuncionarioDTO create(Funcionario funcionario){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(funcionario, FuncionarioDTO.class);
    }
}
