package com.example.locadora.service.aluguel;

import com.example.locadora.model.entity.aluguel.AdicionalLocacao;
import com.example.locadora.model.repository.aluguel.AdicionalLocacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdicionalLocacaoService {
    private AdicionalLocacaoRepository repository;

    public AdicionalLocacaoService(AdicionalLocacaoRepository repository) {
        this.repository = repository;
    }

    public List<AdicionalLocacao> getAdicionaislLocacao() {
        return repository.findAll();
    }

    public Optional<AdicionalLocacao> getAdicionalLocacaoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public AdicionalLocacao salvar(AdicionalLocacao adicionalLocacao) {
        validar(adicionalLocacao);
        return repository.save(adicionalLocacao);
    }

    @Transactional
    public void excluir(AdicionalLocacao adicionalLocacao) {
        Objects.requireNonNull(adicionalLocacao.getId());
        repository.delete(adicionalLocacao);
    }

    public void validar(AdicionalLocacao adicionalLocacao) {

        if(adicionalLocacao.getAdicional() == null || adicionalLocacao.getAdicional().getId() == null || adicionalLocacao.getAdicional().getId() == 0 )
        {
            throw new NullPointerException("AdicionalLocacao com Adicional inválido");
        }
        if(adicionalLocacao.getLocacao() == null || adicionalLocacao.getLocacao().getId() == null || adicionalLocacao.getLocacao().getId() == 0 )
        {
            throw new NullPointerException("AdicionalLocacao com Locacao inválido");
        }
        if( adicionalLocacao.getValor() <=0 )
        {
            throw new NullPointerException("AdicionalLocacao com Valor inválido");
        }

        if (adicionalLocacao.getId() == 0 || adicionalLocacao.getId() == null  ) {
            throw new NullPointerException("AdicionalLocacao inválido");
        }
    }
}