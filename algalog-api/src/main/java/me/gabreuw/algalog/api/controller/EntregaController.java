package me.gabreuw.algalog.api.controller;

import lombok.RequiredArgsConstructor;
import me.gabreuw.algalog.domain.model.Entrega;
import me.gabreuw.algalog.domain.service.SolicitacaoEntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/entregas")
public class EntregaController {

    private final SolicitacaoEntregaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@RequestBody Entrega entrega) {
        return service.solicitar(entrega);
    }

}
