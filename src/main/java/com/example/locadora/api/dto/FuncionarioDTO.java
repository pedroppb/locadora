package com.example.locadora.api.dto;

import com.example.locadora.model.entity.pessoa.Funcionario;
import com.example.locadora.model.entity.pessoa.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FuncionarioDTO {
    private Long id;

    private String nome;
    private String senha;
    private String cpf;
    private String rg;
    private String email;

    private String fixoTipo;
    private String fixoDdd;
    private String fixoNumero;

    private String celularTipo;
    private String celularDdd;
    private String celularNumero;

    private Endereco endereco;
    private String numero;
    private String complemento;
    private String pontoReferencia;

    private long idLoja;
    private long idCargo;

    private LocalDate dataAdmissao;
    private LocalDate dataDemissao;

    public static FuncionarioDTO create(Funcionario funcionario){
        ModelMapper modelMapper = new ModelMapper();
        FuncionarioDTO dto = modelMapper.map(funcionario, FuncionarioDTO.class);

        dto.fixoTipo = funcionario.getFixo().getTipo();
        dto.fixoDdd = funcionario.getFixo().getDdd();
        dto.fixoNumero = funcionario.getFixo().getNumero();

        dto.celularTipo = funcionario.getCelular().getTipo();
        dto.celularDdd = funcionario.getCelular().getDdd();
        dto.celularNumero = funcionario.getCelular().getNumero();

        return dto;
    }
}
