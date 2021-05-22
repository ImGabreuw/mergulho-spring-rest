package me.gabreuw.algalog.domain.service;

import lombok.RequiredArgsConstructor;
import me.gabreuw.algalog.domain.exception.NegocioException;
import me.gabreuw.algalog.domain.model.StatusEntrega;
import me.gabreuw.algalog.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FinalizacaoEntregaService {

    private final EntregaRepository repository;
    private final BuscaEntregaService service;

    @Transactional
    public void finalizar(Long entregaId) {
        var entrega = service.buscar(entregaId);

        entrega.finalizar();

        repository.save(entrega);
    }

}
