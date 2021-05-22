package me.gabreuw.algalog.domain.service;

import lombok.RequiredArgsConstructor;
import me.gabreuw.algalog.domain.exception.EntidadeNaoEncontradaException;
import me.gabreuw.algalog.domain.model.Entrega;
import me.gabreuw.algalog.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BuscaEntregaService {

    private final EntregaRepository repository;

    public Entrega buscar(Long entregaId) {
        return repository
                .findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }

}
