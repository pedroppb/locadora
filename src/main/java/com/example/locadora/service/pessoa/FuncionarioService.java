package com.example.locadora.service.pessoa;

import com.example.locadora.model.entity.pessoa.Funcionario;
import com.example.locadora.model.repository.pessoa.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FuncionarioService {
    private FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> getFuncionarios() {
        return repository.findAll();
    }

    public Optional<Funcionario> getFuncionarioById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Funcionario salvar(Funcionario funcionario) {
        validar(funcionario);
        return repository.save(funcionario);
    }

    @Transactional
    public void excluir(Funcionario funcionario) {
        Objects.requireNonNull(funcionario.getId());
        repository.delete(funcionario);
    }

    public void validar(Funcionario funcionario) {

        if(funcionario.getNome().trim().equals("") || funcionario.getNome() == null )
        {
            throw new NullPointerException("Funcionario com Nome inválido");
        }
        if(funcionario.getCpf().trim().equals("") || funcionario.getCpf() == null )
        {
            throw new NullPointerException("Funcionario com Cpf inválido");
        }
        if(funcionario.getRg().trim().equals("") || funcionario.getRg() == null )
        {
            throw new NullPointerException("Funcionario com Rg inválido");
        }
        if(funcionario.getEmail().trim().equals("") || funcionario.getEmail() == null )
        {
            throw new NullPointerException("Funcionario com Email inválido");
        }
        if(funcionario.getDataAdmissao().equals(null))
        {
            throw new NullPointerException("Funcionario com DataAdmissao inválido");
        }
        if(funcionario.getEndereco() == null || funcionario.getEndereco().getId() == null || funcionario.getEndereco().getId() == 0 )
        {
            throw new NullPointerException("Funcionario com Endereco inválido");
        }
        if(funcionario.getLoja() == null || funcionario.getLoja().getId() == null || funcionario.getLoja().getId() == 0 )
        {
            throw new NullPointerException("Funcionario com Loja inválido");
        }
        if(funcionario.getCargo() == null || funcionario.getCargo().getId() == null || funcionario.getCargo().getId() == 0 )
        {
            throw new NullPointerException("Funcionario com Cargo inválido");
        }
    }
}