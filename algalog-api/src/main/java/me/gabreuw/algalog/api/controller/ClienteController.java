package me.gabreuw.algalog.api.controller;

import me.gabreuw.algalog.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {

    @GetMapping(path = "/clientes")
    public List<Cliente> listar() {
        var joao = new Cliente(
                1L,
                "João",
                "joaodascouves@algaworks.com",
                "34 99999-1111"
        );
        var maria = new Cliente(
                2L,
                "Maria",
                "mariadasilva@algaworks.com",
                "34 97777-2222"
        );

        return List.of(joao, maria);
    }

}
