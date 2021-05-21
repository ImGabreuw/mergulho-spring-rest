package me.gabreuw.algalog.domain.service;

import lombok.RequiredArgsConstructor;
import me.gabreuw.algalog.domain.exception.NegocioException;
import me.gabreuw.algalog.domain.model.Cliente;
import me.gabreuw.algalog.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CatalogoClienteService {

    private final ClienteRepository repository;

    public Cliente buscar(Long clienteId) {
        return repository
                .findById(clienteId)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        var emailEmUso = repository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
        }

        return repository.save(cliente);
    }

    @Transactional
    public void deletar(Long clienteId) {
        repository.deleteById(clienteId);
    }
}
