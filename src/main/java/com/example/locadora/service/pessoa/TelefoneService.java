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

    public List<Telefone> getTelefone() {
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
        if (telefone.getId() == 0 || telefone.getDdd() == null || telefone.getNumero() == null || telefone.getTipo() == null ) {
            throw new NullPointerException("Disciplina inválida");
        }
    }
}
