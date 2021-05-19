package me.gabreuw.algalog.domain.repository;

import me.gabreuw.algalog.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);
    List<Cliente> findByNomeContaining(String nome);

}