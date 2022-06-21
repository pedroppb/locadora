package com.example.locadora.api.dto;

import com.example.locadora.model.entity.aluguel.Loja;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class LojaDTO {
    private Long id;
    private String nome;
    private String email;
    private String horaAbertura;
    private String horaFechamento;

    private String fixoTipo;
    private String fixoDdd;
    private String fixoNumero;

    private String nomePais;
    private String nomeEstado;
    private String nomeCidade;
    private String cep;
    private String rua;
    private String bairro;
    private String numero;
    private String complemento;
    private String pontoReferencia;

    public static LojaDTO create(Loja loja){
        ModelMapper modelMapper = new ModelMapper();
        LojaDTO dto = modelMapper.map(loja, LojaDTO.class);

        dto.fixoTipo = loja.getTelefone().getTipo();
        dto.fixoDdd = loja.getTelefone().getDdd();
        dto.fixoNumero = loja.getTelefone().getNumero();

        dto.nomePais = loja.getEndereco().getCidade().getEstado().getPais().getNome();
        dto.nomeEstado = loja.getEndereco().getCidade().getEstado().getNome();
        dto.nomeCidade = loja.getEndereco().getCidade().getNome();
        dto.cep = loja.getEndereco().getCep();
        dto.rua = loja.getEndereco().getRua();
        dto.bairro = loja.getEndereco().getBairro();

        return dto;
    }
}
