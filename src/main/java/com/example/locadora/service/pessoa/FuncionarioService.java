package com.example.locadora.service.pessoa;

import com.example.locadora.model.entity.pessoa.Funcionario;
import com.example.locadora.model.repository.pessoa.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FuncionarioService {
    private FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> getFuncionario() {
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
        String[] othervar = new String[] {"Endereco","Loja","Cargo","DataAdmissao"};
        String[] strvar = new String[] {"Nome","Senha","Cpf","Rg","Email"};

        Class classe = funcionario.getClass();
        Object objeto = funcionario;
        try {
            objeto = classe.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Serviço inexistente");
        }
        if (!(objeto instanceof Funcionario) ) {
            throw new IllegalArgumentException("Serviço inválido");
        }
        for ( String str : strvar) {

    }

       /* for (String str : othervar ) {
            String metodo = "get"+str+"()";
            String metodoid = "get"+str+"().getId()";
            if ( funcionario.&metodo == null || funcionario.&metodoid == null || funcionario.&metodoid == 0
            ) throw new NullPointerException("funcionario com %s inválido",str);
        }*/

        if (funcionario.getId() == 0 || funcionario.getId() == null  ) {
            throw new NullPointerException("funcionario inválido");
        }
    }
}