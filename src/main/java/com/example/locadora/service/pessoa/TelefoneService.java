package com.example.locadora.service.pessoa;

import com.example.locadora.model.entity.pessoa.Telefone;
import com.example.locadora.model.repository.pessoa.TelefoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TelefoneService {
    private TelefoneRepository repository;

    public TelefoneService(TelefoneRepository repository) {
        this.repository = repository;
    }

    public List<Telefone> getTelefones() {
        return repository.findAll();
    }

    public Optional<Telefone> getTelefoneById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Telefone salvar(Telefone telefone) {
        validar(telefone);
        return repository.save(telefone);
    }

    @Transactional
    public void excluir(Telefone telefone) {
        Objects.requireNonNull(telefone.getId());
        repository.delete(telefone);
    }

    public void validar(Telefone telefone) {
        if(telefone.getNumero().trim().equals("") || telefone.getNumero() == null )
        {
            throw new NullPointerException("Telefone com Numero inválido");
        }
        if(telefone.getTipo().trim().equals("") || telefone.getTipo() == null )
        {
            throw new NullPointerException("Telefone com Tipo inválido");
        }
        if(telefone.getDdd().trim().equals("") || telefone.getDdd() == null )
        {
            throw new NullPointerException("Telefone com DDD inválido");
        }
    }
}
