package me.gabreuw.algalog.api.controller;

import me.gabreuw.algalog.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
public class ClienteController {

    @PersistenceContext // instanciação automática de uma EntityManager
    private EntityManager manager;

    @GetMapping(path = "/clientes")
    public List<Cliente> listar() {
        return manager.createQuery("FROM Cliente", Cliente.class)
                .getResultList();
    }

}
