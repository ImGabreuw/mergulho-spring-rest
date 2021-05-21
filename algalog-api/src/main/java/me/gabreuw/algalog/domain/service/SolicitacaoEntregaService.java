package me.gabreuw.algalog.domain.service;

import lombok.RequiredArgsConstructor;
import me.gabreuw.algalog.domain.model.Entrega;
import me.gabreuw.algalog.domain.model.StatusEntrega;
import me.gabreuw.algalog.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private final EntregaRepository repository;
    private final CatalogoClienteService service;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        entrega.setCliente(service.buscar(entrega.getCliente().getId()));
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return repository.save(entrega);
    }

}
