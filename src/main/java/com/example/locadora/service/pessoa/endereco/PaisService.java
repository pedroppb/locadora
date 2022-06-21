package com.example.locadora.service.pessoa.endereco;

import com.example.locadora.model.entity.pessoa.endereco.Pais;
import com.example.locadora.model.repository.pessoa.endereco.PaisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PaisService {
    private PaisRepository repository;

    public PaisService(PaisRepository repository) {
        this.repository = repository;
    }

    public List<Pais> getPaises() {
        return repository.findAll();
    }

    public Optional<Pais> getPaisById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Pais salvar(Pais pais) {
        validar(pais);
        return repository.save(pais);
    }

    @Transactional
    public void excluir(Pais pais) {
        Objects.requireNonNull(pais.getId());
        repository.delete(pais);
    }

    public void validar(Pais pais) {

        if(pais.getNome().trim().equals("") || pais.getNome() == null )
        {
            throw new NullPointerException("Pais com Nome inválido");
        }

        if (pais.getId() == 0 || pais.getId() == null  ) {
            throw new NullPointerException("Pais inválido");
        }
    }
}