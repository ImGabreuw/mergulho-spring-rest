package me.gabreuw.algalog.api.controller;

import lombok.RequiredArgsConstructor;
import me.gabreuw.algalog.domain.model.Cliente;
import me.gabreuw.algalog.domain.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    @GetMapping
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @GetMapping(path = "/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        return repository
                .findById(clienteId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente cliente) {
        return repository.save(cliente);
    }

    @PutMapping(path = "/{clienteId}")
    public ResponseEntity<Cliente> atualizar(
            @PathVariable Long clienteId,
            @RequestBody Cliente cliente
    ) {
        if (!repository.existsById(clienteId)) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        cliente.setId(clienteId);

        return ResponseEntity
                .ok(repository.save(cliente));
    }

    @DeleteMapping(path = "/{clienteId}")
    public ResponseEntity<Void> deletar(@PathVariable Long clienteId) {
        if (!repository.existsById(clienteId)) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        repository.deleteById(clienteId);

        return ResponseEntity
                .noContent()
                .build();
    }
}
