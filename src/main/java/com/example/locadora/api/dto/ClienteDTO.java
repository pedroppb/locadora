package com.example.locadora.api.dto;

import com.example.locadora.model.entity.pessoa.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private LocalDate cnhValidade;
    private String email;

    private String fixoTipo;
    private String fixoDdd;
    private String fixoNumero;

    private String celularTipo;
    private String celularDdd;
    private String celularNumero;

    private String outroTipo;
    private String outroDdd;
    private String outroNumero;

    private String nomePais;
    private String nomeEstado;
    private String nomeCidade;
    private String cep;
    private String rua;
    private String bairro;
    private String numero;
    private String complemento;
    private String pontoReferencia;

    public static ClienteDTO create(Cliente cliente){
        ModelMapper modelMapper = new ModelMapper();
        ClienteDTO dto = modelMapper.map(cliente, ClienteDTO.class);
        if(cliente.getFixo() != null) {
            dto.fixoTipo = cliente.getFixo().getTipo();
            dto.fixoDdd = cliente.getFixo().getDdd();
            dto.fixoNumero = cliente.getFixo().getNumero();
        }
        if(cliente.getCelular() != null) {
            dto.celularTipo = cliente.getCelular().getTipo();
            dto.celularDdd = cliente.getCelular().getDdd();
            dto.celularNumero = cliente.getCelular().getNumero();
        }

        if(cliente.getOutro() != null) {
            dto.outroTipo = cliente.getOutro().getTipo();
            dto.outroDdd = cliente.getOutro().getDdd();
            dto.outroNumero = cliente.getOutro().getNumero();
        }
        dto.nomePais = cliente.getEndereco().getCidade().getEstado().getPais().getNome();
        dto.nomeEstado = cliente.getEndereco().getCidade().getEstado().getNome();
        dto.nomeCidade = cliente.getEndereco().getCidade().getNome();
        dto.cep = cliente.getEndereco().getCep();
        dto.rua = cliente.getEndereco().getRua();
        dto.bairro = cliente.getEndereco().getBairro();

        return dto;
    }
}
