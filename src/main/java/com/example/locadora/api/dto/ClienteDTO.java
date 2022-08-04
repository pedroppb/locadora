package com.example.locadora.api.dto;

import com.example.locadora.model.entity.pessoa.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteDTO {
    private Long id;
    private String nome;
    private String senha;
    private Integer tipo;
    private String cnpj;
    private String nomeContato;
    private String emailContato;
    private String cpf;
    private String rg;
    private String cnh;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate cnhValidade;
    private String email;

    private Long idFixo;
    private Long idCelular;
    private Long idOutro;

    private Long idEndereco;
    private String numero;
    private String complemento;
    private String pontoReferencia;

    public static ClienteDTO create(Cliente cliente){
        ModelMapper modelMapper = new ModelMapper();
        ClienteDTO dto = modelMapper.map(cliente, ClienteDTO.class);

        return dto;
    }
}
