package com.example.locadora.service.carro;

        import com.example.locadora.model.entity.carro.Categoria;
        import com.example.locadora.model.repository.carro.CategoriaRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class CategoriaService {
    private CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> getCategorias() {
        return repository.findAll();
    }

    public Optional<Categoria> getCategoriaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Categoria salvar(Categoria categoria) {
        validar(categoria);
        return repository.save(categoria);
    }

    @Transactional
    public void excluir(Categoria categoria) {
        Objects.requireNonNull(categoria.getId());
        repository.delete(categoria);
    }

    public void validar(Categoria categoria) {

        if(categoria.getNome().trim().equals("") || categoria.getNome() == null )
        {
            throw new NullPointerException("Categoria com Nome inválido");
        }
        if(categoria.getDescricao().trim().equals("") || categoria.getDescricao () == null )
        {
            throw new NullPointerException("Categoria com Descricao inválido");
        }

        if (categoria.getId() == 0 || categoria.getId() == null  ) {
            throw new NullPointerException("Categoria inválido");
        }
    }
}