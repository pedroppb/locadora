package com.example.locadora.service.aluguel;

        import com.example.locadora.model.entity.aluguel.Reserva;
        import com.example.locadora.model.repository.aluguel.ReservaRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class ReservaService {
    private ReservaRepository repository;

    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }

    public List<Reserva> getReservas() {
        return repository.findAll();
    }

    public Optional<Reserva> getReservaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Reserva salvar(Reserva reserva) {
        validar(reserva);
        return repository.save(reserva);
    }

    @Transactional
    public void excluir(Reserva reserva) {
        Objects.requireNonNull(reserva.getId());
        repository.delete(reserva);
    }

    public void validar(Reserva reserva) {
        if(reserva.getCliente() == null || reserva.getCliente().getId() == null || reserva.getCliente().getId() == 0 )
        {
            throw new NullPointerException("Reserva com Cliente inválido");
        }
              if(reserva.getLojaRetirada() == null || reserva.getLojaRetirada().getId() == null || reserva.getLojaRetirada().getId() == 0 )
        {
            throw new NullPointerException("Reserva com LojaRetirada inválido");
        }
        if(reserva.getDataHoraRetirada() == null)
        {
            throw new NullPointerException("Reserva com DataHoraRetirada inválido");
        }
               if(reserva.getLojaProgramada() == null || reserva.getLojaProgramada().getId() == null || reserva.getLojaProgramada().getId() == 0 )
        {
            throw new NullPointerException("Reserva com LojaProgramada inválido");
        }
        if(reserva.getDataHoraProgramada() == null)
        {
            throw new NullPointerException("Reserva com DataHoraProgramada inválido");
        }
        if(reserva.getTipoAluguel() == null || reserva.getTipoAluguel().getId() == null || reserva.getTipoAluguel().getId() == 0 )
        {
            throw new NullPointerException("Reserva com TipoAluguel inválido");
        }
        if(reserva.getCategoria() == null || reserva.getCategoria().getId() == null || reserva.getCategoria().getId() == 0 )
        {
            throw new NullPointerException("Reserva com Categoria inválido");
        }
        if( reserva.getValor() <=0 )
        {
            throw new NullPointerException("Reserva com valor inválido");
        }
    }
}