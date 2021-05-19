package me.gabreuw.algalog.api.controller;

import lombok.RequiredArgsConstructor;
import me.gabreuw.algalog.domain.model.Cliente;
import me.gabreuw.algalog.domain.repository.ClienteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ClienteController {

    private final ClienteRepository repository;

    @GetMapping(path = "/clientes")
    public List<Cliente> listar() {
        return repository.findAll();
    }

}
