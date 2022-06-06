package com.example.locadora.api.dto;

import com.example.locadora.model.entity.aluguel.Loja;
import com.example.locadora.model.entity.carro.Carro;
import com.example.locadora.model.entity.carro.Categoria;
import com.example.locadora.model.entity.carro.Modelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarroDTO {
    private Long id;
    private String placa;
    private String nomePais;
    private String nomeEstado;
    private String nomeCidade;
    private String chassi;
    private Categoria categoria;
    private Loja loja;
    private Modelo modelo;
    private int anoFabricacao;
    private float odometro;

    public static CarroDTO create(Carro carro){
        ModelMapper modelMapper = new ModelMapper();
        CarroDTO dto = modelMapper.map(carro, CarroDTO.class);

        dto.placa = carro.getPlaca();
        dto.nomeEstado = carro.getEstado().getNome();
        dto.chassi = carro.getChassi();
        dto.categoria = carro.getCategoria();
        dto.loja = carro.getLoja();
        dto.modelo = carro.getModelo();
        dto.anoFabricacao = carro.getAnoFabricacao();
        dto.odometro = carro.getOdometro();

        return dto;
    }
}
