package com.example.locadora.service.aluguel;

        import com.example.locadora.model.entity.aluguel.Loja;
        import com.example.locadora.model.repository.aluguel.LojaRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class LojaService {
    private LojaRepository repository;

    public LojaService(LojaRepository repository) {
        this.repository = repository;
    }

    public List<Loja> getLojas() {
        return repository.findAll();
    }

    public Optional<Loja> getLojaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Loja salvar(Loja loja) {
        validar(loja);
        return repository.save(loja);
    }

    @Transactional
    public void excluir(Loja loja) {
        Objects.requireNonNull(loja.getId());
        repository.delete(loja);
    }

    public void validar(Loja loja) {
        if(loja.getNome().trim().equals("") || loja.getNome() == null )
        {
            throw new NullPointerException("loja com Nome inválido");
        }
        if(loja.getEmail().trim().equals("") || loja.getEmail() == null )
        {
            throw new NullPointerException("loja com Email inválido");
        }
        if(loja.getHoraAbertura().trim().equals("") || loja.getHoraAbertura() == null )
        {
            throw new NullPointerException("loja com HoraAbertura inválido");
        }
        if(loja.getHoraFechamento().trim().equals("") || loja.getHoraFechamento() == null )
        {
            throw new NullPointerException("loja com HoraFechamento inválido");
        }
        if(loja.getEndereco() == null || loja.getEndereco().getId() == null || loja.getEndereco().getId() == 0 ) {
            throw new NullPointerException("Loja com Endereco inválido");
        }
        if(loja.getTelefone() == null || loja.getTelefone().getId() == null || loja.getTelefone().getId() == 0 )
        {
            throw new NullPointerException("Loja com Telefone inválido");
        }
        if (loja.getId() == 0 || loja.getId() == null  ) {
            throw new NullPointerException("Loja inválido");
        }
    }
}