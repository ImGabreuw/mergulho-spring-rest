package me.gabreuw.algalog.domain.service;

import lombok.RequiredArgsConstructor;
import me.gabreuw.algalog.domain.model.Ocorrencia;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RegistroOcorrenciaService {

    private final BuscaEntregaService service;

    @Transactional // Qualquer alteração no escopo desse @Transactional (método "registrar") o JPA salvará automaticamente após a finalização da transação
    public Ocorrencia registrar(Long entregaId, String descricao) {
        var entrega = service.buscar(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }

}
