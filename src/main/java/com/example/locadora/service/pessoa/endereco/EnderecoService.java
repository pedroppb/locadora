package com.example.locadora.service.pessoa.endereco;

        import com.example.locadora.model.entity.pessoa.endereco.Endereco;
        import com.example.locadora.model.repository.pessoa.endereco.EnderecoRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class EnderecoService {
    private EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public List<Endereco> getEnderecos() {
        return repository.findAll();
    }

    public Optional<Endereco> getEnderecoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Endereco salvar(Endereco endereco) {
        validar(endereco);
        return repository.save(endereco);
    }

    @Transactional
    public void excluir(Endereco endereco) {
        Objects.requireNonNull(endereco.getId());
        repository.delete(endereco);
    }

    public void validar(Endereco endereco) {

        if(endereco.getCep().trim().equals("") || endereco.getCep() == null )
        {
            throw new NullPointerException("Endereco com Cep inválido");
        }
        if(endereco.getRua().trim().equals("") || endereco.getRua() == null )
        {
            throw new NullPointerException("Endereco com rua inválido");
        }
        if(endereco.getBairro().trim().equals("") || endereco.getBairro() == null )
        {
            throw new NullPointerException("Endereco com Bairro inválido");
        }
        if(endereco.getCidade() == null || endereco.getCidade().getId() == null || endereco.getCidade().getId() == 0 )
        {
            throw new NullPointerException("Endereco com Cidade inválido");
        }

        if (endereco.getId() == 0 || endereco.getId() == null  ) {
            throw new NullPointerException("Endereco inválido");
        }
    }
}