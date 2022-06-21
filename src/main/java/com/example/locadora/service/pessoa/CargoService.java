
package com.example.locadora.service.pessoa;

        import com.example.locadora.model.entity.pessoa.Cargo;
        import com.example.locadora.model.repository.pessoa.CargoRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class CargoService {
    private CargoRepository repository;

    public CargoService(CargoRepository repository) {
        this.repository = repository;
    }

    public List<Cargo> getCargos() {
        return repository.findAll();
    }

    public Optional<Cargo> getCargoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Cargo salvar(Cargo cargo) {
        validar(cargo);
        return repository.save(cargo);
    }

    @Transactional
    public void excluir(Cargo cargo) {
        Objects.requireNonNull(cargo.getId());
        repository.delete(cargo);
    }

    public void validar(Cargo cargo) {

        if(cargo.getNome().trim().equals("") || cargo.getNome() == null )
        {
            throw new NullPointerException("Cargo com Nome inválido");
        }
        if(cargo.getSalario() <= 0 || cargo.getSalario() == null )
        {
            throw new NullPointerException("Cargo com Salario inválida");
        }

        if (cargo.getId() == 0 || cargo.getId() == null  ) {
            throw new NullPointerException("Cargo inválido");
        }
    }
}