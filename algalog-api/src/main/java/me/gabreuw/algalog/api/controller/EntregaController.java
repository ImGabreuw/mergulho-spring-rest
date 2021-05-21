package me.gabreuw.algalog.api.controller;

import lombok.RequiredArgsConstructor;
import me.gabreuw.algalog.domain.model.Entrega;
import me.gabreuw.algalog.domain.repository.EntregaRepository;
import me.gabreuw.algalog.domain.service.SolicitacaoEntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/entregas")
public class EntregaController {

    private final EntregaRepository repository;
    private final SolicitacaoEntregaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return service.solicitar(entrega);
    }

    @GetMapping
    public List<Entrega> listar() {
        return repository.findAll();
    }

    @GetMapping(path = "/{entregaId}")
    public ResponseEntity<Entrega> buscar( @PathVariable Long entregaId) {
        return repository.findById(entregaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
